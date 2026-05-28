package com.biblioteca.mapper;



import com.biblioteca.domain.model.BookModel;
import com.biblioteca.dto.response.BookResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class MapperBook {

    private final ModelMapper modelMapper = new ModelMapper();

    public BookResponseDTO bookToResponse(BookModel data){
        return this.modelMapper.map(data, BookResponseDTO.class);
    }

    public Page<BookResponseDTO> booksToResponse(Page<BookModel> data){
        return data.map(books ->this.modelMapper.map(books, BookResponseDTO.class));
    }
}
