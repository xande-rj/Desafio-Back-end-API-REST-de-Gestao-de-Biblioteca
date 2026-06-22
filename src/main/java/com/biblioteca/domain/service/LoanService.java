package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;
import com.biblioteca.domain.model.BookModel;
import com.biblioteca.domain.model.LoanModel;
import com.biblioteca.domain.model.UserModel;
import com.biblioteca.domain.repository.BookRepository;
import com.biblioteca.domain.repository.LoanRepository;
import com.biblioteca.domain.repository.UserRepository;
import com.biblioteca.dto.request.LoanResquestDTO;
import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.exception.LimitBooksException;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.mapper.MapperLoan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private MapperLoan mapperLoan = new MapperLoan();

    public LoanService(UserRepository userRepository, BookRepository bookRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public LoansResponseDTO addLoan(LoanResquestDTO dto) {
        UserModel user = this.userRepository.findById(dto.getIdUser()).orElseThrow(() -> new ResourceNotFoundException("user nao encontrado"));

        BookModel book = this.bookRepository.findById(dto.getIdBook()).orElseThrow(() -> new ResourceNotFoundException("livro nao encontrado"));

        if (user.getLoans().size() >= 3) {
            throw new LimitBooksException("user nao pode ter mais de 3 livros");
        }
        if (book.getStatus() == Status.UNAVAILABLE || book.isRemoved()) {
            new RuntimeException("livro nao esta disponivel para emprestimo");
        }

        LoanModel loanModel = new LoanModel();
        loanModel.setBook(book);
        loanModel.setCreate_at(LocalDateTime.now());
        loanModel.setUpdate_at(LocalDateTime.now());
        loanModel.setUser(user);
        LoanModel newLoan = this.loanRepository.save(loanModel);
        user.getLoans().add(newLoan);


        return mapperLoan.loansResponseDTO(this.userRepository.save(user).getLoans().getLast());
    }
}
