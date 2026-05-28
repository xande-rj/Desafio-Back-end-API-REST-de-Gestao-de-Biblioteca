package com.biblioteca.mapper;

import com.biblioteca.domain.model.CategoryModel;
import com.biblioteca.dto.response.CategoryResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public class MapperCategory {

    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryResponseDTO categoryToResponse(CategoryModel data){
        return this.modelMapper.map(data, CategoryResponseDTO.class);
    }

    public List<CategoryResponseDTO> categorysToResponse(List<CategoryModel> data){
        return data.stream().map(categoryModel -> this.modelMapper.map(categoryModel, CategoryResponseDTO.class)).toList();
    }
}
