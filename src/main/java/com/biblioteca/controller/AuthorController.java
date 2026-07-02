package com.biblioteca.controller;


import com.biblioteca.domain.service.AuthorService;
import com.biblioteca.dto.request.AuthorRequestDTO;
import com.biblioteca.dto.response.AuthorResponseDTO;
import com.biblioteca.mapper.MapperAuthor;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(
        name = "Authors",
        description = "Operations related to the authors"
)
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService authorService) {
        this.service = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody @Valid AuthorRequestDTO data) {
        return new ResponseEntity<AuthorResponseDTO>(this.service.createdAuthor(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        return new ResponseEntity<List<AuthorResponseDTO>> (this.service.getAllAuthors(), HttpStatus.OK);
    }
}
