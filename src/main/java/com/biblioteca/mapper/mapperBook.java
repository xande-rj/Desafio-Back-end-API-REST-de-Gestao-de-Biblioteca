package com.biblioteca.mapper;



import com.biblioteca.domain.model.bookModel;
import com.biblioteca.dto.response.bookResponseDTO;
import org.modelmapper.ModelMapper;

public class mapperBook {

    private final ModelMapper modelMapper = new ModelMapper();

    public bookResponseDTO bookToResponse(bookModel data){
        return this.modelMapper.map(data, bookResponseDTO.class);
    }
}
