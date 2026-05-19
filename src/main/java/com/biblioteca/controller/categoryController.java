package com.biblioteca.controller;


import com.biblioteca.domain.service.categoryService;
import com.biblioteca.dto.request.categoryRequestDTO;
import com.biblioteca.dto.response.categoryResponseDTO;
import com.biblioteca.mapper.mapperCategory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class categoryController {
    private final categoryService service;
    private mapperCategory mapper = new mapperCategory();

    public categoryController(categoryService categoryService){
        this.service = categoryService;
    }

    @PostMapping
    public ResponseEntity<categoryResponseDTO> createdCategory(@Valid @RequestBody categoryRequestDTO data){
        categoryResponseDTO category = this.mapper.categoryToResponse(this.service.createdCategory(data));
        return new ResponseEntity<categoryResponseDTO>(category, HttpStatus.CREATED);
    }
}
