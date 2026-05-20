package com.biblioteca.controller;


import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.service.authorService;
import com.biblioteca.dto.request.authorRequestDTO;
import com.biblioteca.dto.response.authorResponseDTO;
import com.biblioteca.mapper.mapperAuthor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class authorController {
    private final authorService service;
    private final mapperAuthor mapper = new mapperAuthor();

    public authorController(authorService authorService) {
        this.service = authorService;
    }

    @PostMapping
    public ResponseEntity<authorResponseDTO> saveAuthor(@RequestBody @Valid  authorRequestDTO data) {
        authorResponseDTO author = mapper.authorToResponse(this.service.createdAuthor(data));
        return new ResponseEntity<authorResponseDTO>(author, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<authorResponseDTO>> getAllAuthors() {
        List<authorResponseDTO> authors = mapper.authorsToResponse(this.service.getAllAuthors());
        return new ResponseEntity<List<authorResponseDTO>> (authors, HttpStatus.OK);
    }
}
