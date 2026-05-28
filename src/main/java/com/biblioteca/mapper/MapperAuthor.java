package com.biblioteca.mapper;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.dto.response.AuthorResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;


public class mapperAuthor {

 private final ModelMapper modelMapper = new ModelMapper();

 public AuthorResponseDTO authorToResponse(authorModel data) {
  return this.modelMapper.map(data, AuthorResponseDTO.class);
 }

 public List<AuthorResponseDTO> authorsToResponse(List<authorModel> data){
  return data.stream().map(authors -> this.modelMapper.map(authors, AuthorResponseDTO.class)).toList();
 }
}
