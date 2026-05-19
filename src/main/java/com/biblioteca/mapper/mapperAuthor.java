package com.biblioteca.mapper;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.dto.response.authorResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;


public class mapperAuthor {

 private final ModelMapper modelMapper = new ModelMapper();

 public authorResponseDTO authorToResponse(authorModel data) {
  return this.modelMapper.map(data, authorResponseDTO.class);
 }

 public List<authorResponseDTO> authorsToResponse(List<authorModel> data){
  return data.stream().map(authors -> this.modelMapper.map(authors, authorResponseDTO.class)).toList();
 }
}
