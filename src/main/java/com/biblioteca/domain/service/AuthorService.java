package com.biblioteca.domain.service;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.repository.authorRepository;
import com.biblioteca.dto.request.AuthorRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class authorService {
    private final authorRepository repository;

    public authorService(authorRepository authorRepository){
        this.repository =authorRepository;
    }


    public authorModel createdAuthor(AuthorRequestDTO data){
        authorModel author = new authorModel();
        if(repository.existsByNameIgnoreCase(data.getName())){
            throw  new ResourceNotFoundException("A author with that name already exists.");
        }
        author.setName(data.getName());
        author.setDetails(data.getDetails());
        author.setCreated_at(LocalDateTime.now());

        return repository.save(author);
    };


    public List<authorModel> getAllAuthors(){
        return this.repository.findAll();
    };
}
