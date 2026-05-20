package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;
import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.model.bookModel;
import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.domain.repository.authorRepository;
import com.biblioteca.domain.repository.bookRepository;
import com.biblioteca.domain.repository.categoryRepository;
import com.biblioteca.dto.request.bookRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class bookService {
    private final bookRepository bookRepository;
    private final authorRepository authorRepository;
    private final categoryRepository categoryRepository;

    public bookService(bookRepository bookRepository,
                       authorRepository authorRepository,
                       categoryRepository categoryRepository){

        this.bookRepository = bookRepository;
        this.authorRepository  =authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public bookModel createdBook(bookRequestDTO data){
        bookModel book = new bookModel();
        book.setTitle(data.getTitle());
        book.setPages(data.getPages());
        book.setDetails(data.getDetails());
        book.setCreated_at(LocalDateTime.now());
        book.setUpdated_at(LocalDateTime.now());
        book.setStatus(Status.AVAILABLE);

        if(authorRepository.findByNameIgnoreCase(data.getAuthor()).isPresent()){
            authorModel author = authorRepository.findByNameIgnoreCase(data.getAuthor()).get();
            book.setAuthor(author);
        }
if(!categoryRepository.existsByNameIgnoreCase(data.getCategorys())){
    throw new RuntimeException();
}
        book.setCategory(categoryRepository.findByNameIgnoreCase(data.getCategorys()).get());


        return bookRepository.save(book);

    }
    public void getAllBook(){}
    public void getBookById(Long id){}
    public void updateBook(bookRequestDTO data){}
    public void deleteBook(){}
}
