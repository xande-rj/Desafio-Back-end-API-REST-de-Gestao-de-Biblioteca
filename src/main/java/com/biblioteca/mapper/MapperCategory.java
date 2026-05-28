package com.biblioteca.mapper;

import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.dto.response.CategoryResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public class mapperCategory {

    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryResponseDTO categoryToResponse(categoryModel data){
        return this.modelMapper.map(data, CategoryResponseDTO.class);
    }

    public List<CategoryResponseDTO> categorysToResponse(List<categoryModel> data){
        return data.stream().map(categoryModel -> this.modelMapper.map(categoryModel, CategoryResponseDTO.class)).toList();
    }
}
