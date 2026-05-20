package com.biblioteca.controller;

import com.biblioteca.domain.model.bookModel;
import com.biblioteca.domain.service.bookService;
import com.biblioteca.dto.request.bookRequestDTO;
import com.biblioteca.dto.response.bookResponseDTO;
import com.biblioteca.mapper.mapperBook;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class bookController {
    private final bookService service;
    private final mapperBook mapper = new mapperBook();

    public bookController(bookService bookService){
        this.service = bookService;
    }
    @PostMapping
    public ResponseEntity<bookResponseDTO> createdBook(@RequestBody @Valid bookRequestDTO data){
        bookResponseDTO newBook = this.mapper.bookToResponse(service.createdBook(data));

        return new ResponseEntity<bookResponseDTO>(newBook,HttpStatus.CREATED);
    }

}
