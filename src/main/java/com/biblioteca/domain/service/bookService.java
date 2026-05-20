package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Status;

import com.biblioteca.domain.model.bookModel;

import com.biblioteca.domain.repository.authorRepository;
import com.biblioteca.domain.repository.bookRepository;
import com.biblioteca.domain.repository.categoryRepository;
import com.biblioteca.dto.request.bookRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
public class bookService {
    private final bookRepository bookRepository;
    private final authorRepository authorRepository;
    private final categoryRepository categoryRepository;

    public bookService(bookRepository bookRepository,
                       authorRepository authorRepository,
                       categoryRepository categoryRepository) {

        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public bookModel createdBook(bookRequestDTO data) {
        bookModel book = new bookModel();
        if(bookRepository.existsByTitle(data.getTitle())){
            throw  new ResourceNotFoundException("A book with that title already exists.");
        }
        book.setTitle(data.getTitle());
        book.setPages(data.getPages());
        book.setDetails(data.getDetails());
        book.setCreated_at(LocalDateTime.now());
        book.setUpdated_at(LocalDateTime.now());
        book.setStatus(Status.AVAILABLE);
        book.setAuthor(authorRepository.findByNameIgnoreCase(data.getAuthor()).orElseThrow(() -> new ResourceNotFoundException("Author dont exists")));
        book.setCategory(categoryRepository.findByTitleIgnoreCase(data.getCategory()).orElseThrow(() -> new ResourceNotFoundException("Category dont exist")));

        return bookRepository.save(book);

    }

    public void getAllBook() {
    }

    public void getBookById(Long id) {
    }

    public void updateBook(bookRequestDTO data) {
    }

    public void deleteBook() {
    }
}
