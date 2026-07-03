package com.biblioteca.controller;

import com.biblioteca.domain.model.BookModel;
import com.biblioteca.domain.service.BookService;
import com.biblioteca.dto.request.BookRequestDTO;
import com.biblioteca.dto.request.BookUpdateDTO;
import com.biblioteca.dto.response.BookResponseDTO;
import com.biblioteca.mapper.MapperBook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@Tag(
        name = "Books",
        description = "Book-related operations"
)
public class BookController {
    private final BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @PostMapping
    @Operation(
            summary = "Register Book",
            description = "Creates a book in the database."
    )
    public ResponseEntity<BookResponseDTO> createdBooks(@RequestBody @Valid BookRequestDTO data) {
        return new ResponseEntity<BookResponseDTO>(service.createdBook(data), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get All Book",
            description = "Grab all the books."
    )
    public Page<BookResponseDTO> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "") String search
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return this.service.getAllBook(pageable, search);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Book",
            description = "Get book by ID."
    )
    public ResponseEntity<BookResponseDTO> getBooksById(@PathVariable(required = true) Long id) {
        return new ResponseEntity<>(this.service.getBookById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update Book",
            description = "updates a book in the database."
    )
    public ResponseEntity<BookResponseDTO> updateBooks(
            @PathVariable Long id,
            @RequestBody BookUpdateDTO data
    ) {
        return new ResponseEntity<>(this.service.updateBook(id, data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Soft delete Book",
            description = "Removes the book from future loans"
    )
    public ResponseEntity<?> deleteBooks(@PathVariable Long id) {

        return new ResponseEntity<>( this.service.deleteBook(id), HttpStatus.OK);
    }

}
