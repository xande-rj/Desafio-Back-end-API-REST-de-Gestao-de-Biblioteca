package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;
import com.biblioteca.domain.model.BookModel;
import com.biblioteca.domain.model.LoanModel;
import com.biblioteca.domain.model.UserModel;
import com.biblioteca.domain.repository.BookRepository;
import com.biblioteca.domain.repository.LoanRepository;
import com.biblioteca.domain.repository.UserRepository;
import com.biblioteca.dto.request.LoanResquestDTO;
import com.biblioteca.dto.response.LoanReturnResponseDTO;
import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.exception.LimitBooksException;
import com.biblioteca.exception.PenaltyLoanException;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.exception.UnavailableBooksException;
import com.biblioteca.mapper.MapperLoan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final MapperLoan mapperLoan = new MapperLoan();

    public LoanService(UserRepository userRepository, BookRepository bookRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public LoansResponseDTO addLoan(LoanResquestDTO dto) {
        UserModel user = this.userRepository.findById(dto.getIdUser()).orElseThrow(()
                -> new ResourceNotFoundException("User not found."));

        BookModel book = this.bookRepository.findById(dto.getIdBook()).orElseThrow(()
                -> new ResourceNotFoundException("Book not found."));

        if (user.getLoans().size() >= 3) {
            throw new LimitBooksException("A user cannot have more than 3 books.");
        }
        for (LoanModel l : user.getLoans()) {
            long periodo = ChronoUnit.DAYS.between(l.getCreateAt(), LocalDateTime.now());
            if (periodo >= 14)
                throw new PenaltyLoanException("Late fee for waiting time :", mapperLoan.loansResponseDTO(l));
        }
        if (book.getStatusBook() == Status.UNAVAILABLE || book.isRemovedBook()) {
            throw new UnavailableBooksException("The book is not available for loan.");
        }

        LoanModel loanModel = new LoanModel();
        loanModel.setBook(book);
        loanModel.setCreateAt(LocalDateTime.now());
        loanModel.setUpdateAt(LocalDateTime.now());
        loanModel.setUser(user);
        LoanModel newLoan = this.loanRepository.save(loanModel);
        user.getLoans().add(newLoan);
        book.setStatusBook(Status.UNAVAILABLE);
        book.setUpdatedAt(LocalDateTime.now());

        this.bookRepository.save((book));

        return mapperLoan.loansResponseDTO(this.userRepository.save(user).getLoans().getLast());
    }

    public LoanReturnResponseDTO loanReturn(Long id) {

        LoanModel loan = this.loanRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Loan not found."));

        long periodo = ChronoUnit.DAYS.between(loan.getCreateAt(), LocalDateTime.now());
        float multa=0F;
        if (periodo >= 14) {
            long i = periodo - 14;
            multa+= i * 0.5F;
        }
        loan.setUpdateAt(LocalDateTime.now());
                loan.setPayDay( LocalDateTime.now());

        Optional<BookModel> bookModel = this.bookRepository.findById(loan.getBook().getId());
        bookModel.get().setStatusBook(Status.AVAILABLE);
        bookModel.get().setUpdatedAt(LocalDateTime.now());

        if(loan.getUser()==null){
            throw new ResourceNotFoundException("User not found.");
        }
        UserModel userModel = this.userRepository.findById(loan.getUser().getId()).orElseThrow(()
                -> new ResourceNotFoundException("User not found."));
        
        loan.setUser(null);
        loan.setHistorical_user(userModel);
        userModel.getHistorical().add(loan);
        this.userRepository.save(userModel);
        this.bookRepository.save(bookModel.get());
         this.loanRepository.save(loan);

        return new LoanReturnResponseDTO(multa);
    }

    public List<LoansResponseDTO>overdueLoan(){
        List<LoanModel> loans = new ArrayList<>();
        List<UserModel> users = this.userRepository.findAll();

        for(UserModel u :users){
            for(LoanModel l : u.getLoans()){
                long periodo = ChronoUnit.DAYS.between(l.getCreateAt(), LocalDateTime.now());
                if(periodo>=14) {
                    loans.add(l);
                }
            }
        }
        return mapperLoan.listLoanResponse(loans);
    }
}
