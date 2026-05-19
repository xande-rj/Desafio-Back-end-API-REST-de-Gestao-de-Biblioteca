package com.biblioteca.domain.service;



import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.domain.repository.categoryRepository;
import com.biblioteca.dto.request.authorRequestDTO;
import com.biblioteca.dto.request.categoryRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class categoryService {
    private final categoryRepository repository;

    public categoryService(categoryRepository categoryRepository){
        this.repository =categoryRepository;
    }

    public categoryModel createdCategory(categoryRequestDTO data){
        categoryModel category = new categoryModel();
        category.setName(data.getName());
        category.setDetails(data.getDetails());
        category.setCreated_at(LocalDateTime.now());
        return repository.save(category);
    };


//    public List<categoryModel> getAllCategorys(){
//        return this.repository.findAll();
//    };
}
