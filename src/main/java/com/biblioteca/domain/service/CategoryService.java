package com.biblioteca.domain.service;



import com.biblioteca.domain.model.CategoryModel;
import com.biblioteca.domain.repository.CategoryRepository;
import com.biblioteca.dto.request.CategoryRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository){
        this.repository =categoryRepository;
    }

    public CategoryModel createdCategory(CategoryRequestDTO data){
        CategoryModel category = new CategoryModel();
        if(repository.existsByTitleIgnoreCase(data.getTitle())){
            throw  new ResourceNotFoundException("A category with that title already exists.");
        }
        category.setTitle(data.getTitle());
        category.setCreated_at(LocalDateTime.now());
        return repository.save(category);
    };


    public List<CategoryModel> getAllCategorys(){
        return this.repository.findAll();
    };
}
