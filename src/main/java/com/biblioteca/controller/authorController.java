package com.biblioteca.controller;


import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.service.authorService;
import com.biblioteca.dto.request.authorRequestDTO;
import com.biblioteca.dto.response.authorResponseDTO;
import com.biblioteca.mapper.mapperAuthor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class authorController {
    private final authorService service;
    private final mapperAuthor mapper = new mapperAuthor();

    public authorController(authorService authorService) {
        this.service = authorService;
    }

    @PostMapping("/authors")
    public ResponseEntity< authorResponseDTO> saveAuthor(@RequestBody authorRequestDTO data) {
        authorResponseDTO author = mapper.authorToDTO(this.service.createdAuthor(data));

        return new ResponseEntity<authorResponseDTO>(author, HttpStatus.CREATED);
    }
}
