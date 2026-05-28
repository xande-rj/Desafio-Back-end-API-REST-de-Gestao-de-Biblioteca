package com.biblioteca.domain.service;

import com.biblioteca.domain.model.AuthorModel;
import com.biblioteca.domain.repository.AuthorRepository;
import com.biblioteca.dto.request.AuthorRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository authorRepository){
        this.repository =authorRepository;
    }


    public AuthorModel createdAuthor(AuthorRequestDTO data){
        AuthorModel author = new AuthorModel();
        if(repository.existsByNameIgnoreCase(data.getName())){
            throw  new ResourceNotFoundException("A author with that name already exists.");
        }
        author.setName(data.getName());
        author.setDetails(data.getDetails());
        author.setCreated_at(LocalDateTime.now());

        return repository.save(author);
    };


    public List<AuthorModel> getAllAuthors(){
        return this.repository.findAll();
    };
}
