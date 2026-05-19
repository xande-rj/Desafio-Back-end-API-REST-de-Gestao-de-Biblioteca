package com.biblioteca.mapper;

import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.dto.response.categoryResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public class mapperCategory {

    private final ModelMapper modelMapper = new ModelMapper();

    public categoryResponseDTO categoryToResponse(categoryModel data){
        return this.modelMapper.map(data, categoryResponseDTO.class);
    }

    public List<categoryResponseDTO> categorysToResponse(List<categoryModel> data){
        return data.stream().map(categoryModel -> this.modelMapper.map(categoryModel, categoryResponseDTO.class)).toList();
    }
}
