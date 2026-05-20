package com.biblioteca.domain.service;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.repository.authorRepository;
import com.biblioteca.dto.request.authorRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class authorService {
    private final authorRepository repository;

    public authorService(authorRepository authorRepository){
        this.repository =authorRepository;
    }


    public authorModel createdAuthor(authorRequestDTO data){
        authorModel author = new authorModel();
        author.setName(data.getName());
        author.setDetails(data.getDetails());
        author.setCreated_at(LocalDateTime.now());

        return repository.save(author);
    };


    public List<authorModel> getAllAuthors(){
        return this.repository.findAll();
    };
}
