package com.biblioteca.controller;


import com.biblioteca.domain.service.CategoryService;
import com.biblioteca.dto.request.CategoryRequestDTO;
import com.biblioteca.dto.response.CategoryResponseDTO;
import com.biblioteca.mapper.MapperCategory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;


    public CategoryController(CategoryService categoryService){
        this.service = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createdCategory(@Valid @RequestBody CategoryRequestDTO data){
        return new ResponseEntity<CategoryResponseDTO>(this.service.createdCategory(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory(){

        return new ResponseEntity<List<CategoryResponseDTO>>(this.service.getAllCategory(), HttpStatus.OK);
    }
}
