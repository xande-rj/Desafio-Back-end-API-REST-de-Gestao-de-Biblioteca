package com.biblioteca.mapper;

import com.biblioteca.domain.model.categoryModel;
import com.biblioteca.dto.response.categoryResponseDTO;
import org.modelmapper.ModelMapper;

public class mapperCategory {

    private final ModelMapper modelMapper = new ModelMapper();

    public categoryResponseDTO categoryToResponse(categoryModel data){
        return this.modelMapper.map(data, categoryResponseDTO.class);
    }
}
