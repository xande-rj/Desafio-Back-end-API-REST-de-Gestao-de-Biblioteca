package com.biblioteca.controller;

import com.biblioteca.domain.model.BookModel;
import com.biblioteca.domain.service.BookService;
import com.biblioteca.dto.request.BookRequestDTO;
import com.biblioteca.dto.request.BookUpdateDTO;
import com.biblioteca.dto.response.BookResponseDTO;
import com.biblioteca.mapper.MapperBook;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    private final MapperBook mapper = new MapperBook();

    public BookController(BookService bookService){
        this.service = bookService;
    }
    @PostMapping
    public ResponseEntity<BookResponseDTO> createdBook(@RequestBody @Valid BookRequestDTO data){
        BookResponseDTO newBook = this.mapper.bookToResponse(service.createdBook(data));

        return new ResponseEntity<BookResponseDTO>(newBook,HttpStatus.CREATED);
    }
    @GetMapping
    public Page<BookResponseDTO> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "") String search
    ){
        Pageable pageable = PageRequest.of(page,size);

            Page<BookModel> book = this.service.getAllBook(pageable,search);

       return this.mapper.booksToResponse(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable(required = true) Long id){
        BookResponseDTO book =this.mapper.bookToResponse( this.service.getBookById(id));

        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long id,
            @RequestBody BookUpdateDTO data
    ){
        BookResponseDTO newBook = this.mapper.bookToResponse(this.service.updateBook(id,data));
        return new ResponseEntity<>( newBook ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        BookResponseDTO book =this.mapper.bookToResponse( this.service.deleteBook(id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
