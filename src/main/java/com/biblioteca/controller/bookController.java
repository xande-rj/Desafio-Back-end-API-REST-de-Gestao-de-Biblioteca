package com.biblioteca.controller;

import com.biblioteca.domain.model.bookModel;
import com.biblioteca.domain.service.bookService;
import com.biblioteca.dto.request.bookRequestDTO;
import com.biblioteca.dto.response.bookResponseDTO;
import com.biblioteca.mapper.mapperBook;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping
    public Page<bookResponseDTO> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "") String search
    ){
        Pageable pageable = PageRequest.of(page,size);

            Page<bookModel> book = this.service.getAllBook(pageable,search);

       return this.mapper.booksToResponse(book);
    }

}
