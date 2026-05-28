package com.biblioteca.mapper;

import com.biblioteca.domain.model.AuthorModel;
import com.biblioteca.dto.response.AuthorResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;


public class MapperAuthor {

 private final ModelMapper modelMapper = new ModelMapper();

 public AuthorResponseDTO authorToResponse(AuthorModel data) {
  return this.modelMapper.map(data, AuthorResponseDTO.class);
 }

 public List<AuthorResponseDTO> authorsToResponse(List<AuthorModel> data){
  return data.stream().map(authors -> this.modelMapper.map(authors, AuthorResponseDTO.class)).toList();
 }
}
