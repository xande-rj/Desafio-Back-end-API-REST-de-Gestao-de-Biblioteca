package com.biblioteca.controller;


import com.biblioteca.domain.service.AuthorService;
import com.biblioteca.dto.request.AuthorRequestDTO;
import com.biblioteca.dto.response.AuthorResponseDTO;
import com.biblioteca.mapper.MapperAuthor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;
    private final MapperAuthor mapper = new MapperAuthor();

    public AuthorController(AuthorService authorService) {
        this.service = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody @Valid AuthorRequestDTO data) {
        AuthorResponseDTO author = mapper.authorToResponse(this.service.createdAuthor(data));
        return new ResponseEntity<AuthorResponseDTO>(author, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = mapper.authorsToResponse(this.service.getAllAuthors());
        return new ResponseEntity<List<AuthorResponseDTO>> (authors, HttpStatus.OK);
    }
}
