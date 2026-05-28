package com.biblioteca.domain.service;



import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.domain.repository.categoryRepository;
import com.biblioteca.dto.request.CategoryRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class categoryService {
    private final categoryRepository repository;

    public categoryService(categoryRepository categoryRepository){
        this.repository =categoryRepository;
    }

    public categoryModel createdCategory(CategoryRequestDTO data){
        categoryModel category = new categoryModel();
        if(repository.existsByTitleIgnoreCase(data.getTitle())){
            throw  new ResourceNotFoundException("A category with that title already exists.");
        }
        category.setTitle(data.getTitle());
        category.setCreated_at(LocalDateTime.now());
        return repository.save(category);
    };


    public List<categoryModel> getAllCategorys(){
        return this.repository.findAll();
    };
}
