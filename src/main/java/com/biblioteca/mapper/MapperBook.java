package com.biblioteca.mapper;



import com.biblioteca.domain.model.bookModel;
import com.biblioteca.dto.response.BookResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class mapperBook {

    private final ModelMapper modelMapper = new ModelMapper();

    public BookResponseDTO bookToResponse(bookModel data){
        return this.modelMapper.map(data, BookResponseDTO.class);
    }

    public Page<BookResponseDTO> booksToResponse(Page<bookModel> data){
        return data.map(books ->this.modelMapper.map(books, BookResponseDTO.class));
    }
}
