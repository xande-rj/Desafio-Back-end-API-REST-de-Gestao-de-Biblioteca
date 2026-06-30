package com.biblioteca.domain.service;



import com.biblioteca.domain.model.CategoryModel;
import com.biblioteca.domain.repository.CategoryRepository;
import com.biblioteca.dto.request.CategoryRequestDTO;
import com.biblioteca.dto.response.CategoryResponseDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.mapper.MapperCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final MapperCategory mapper = new MapperCategory();

    public CategoryService(CategoryRepository categoryRepository){
        this.repository =categoryRepository;
    }

    public CategoryResponseDTO createdCategory(CategoryRequestDTO data){
        CategoryModel category = new CategoryModel();
        if(repository.existsByTitleCategoryIgnoreCase(data.getTitle())){
            throw  new ResourceNotFoundException("A category with that title already exists.");
        }
        category.setTitleCategory(data.getTitle());
        category.setCreatedAt(LocalDateTime.now());
        return this.mapper.categoryToResponse(repository.save(category));
    };


    public List<CategoryResponseDTO> getAllCategorys(){
        return this.mapper.categorysToResponse( this.repository.findAll());
    };
}
