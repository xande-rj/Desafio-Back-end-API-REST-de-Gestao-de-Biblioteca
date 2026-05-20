package com.biblioteca.controller;

import com.biblioteca.domain.model.bookModel;
import com.biblioteca.domain.service.bookService;
import com.biblioteca.dto.request.bookRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class bookController {
    private final bookService service;

    public bookController(bookService bookService){
        this.service = bookService;
    }
    @PostMapping
    public bookModel createdBook(@RequestBody bookRequestDTO data){
        return service.createdBook(data);
    }

}
