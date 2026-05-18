package com.biblioteca.mapper;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.dto.response.authorResponseDTO;
import org.modelmapper.ModelMapper;


public class mapperAuthor {

 private final ModelMapper modelMapper = new ModelMapper();

 public authorResponseDTO authorToDTO(authorModel data) {
  return this.modelMapper.map(data, authorResponseDTO.class);
 }
}
