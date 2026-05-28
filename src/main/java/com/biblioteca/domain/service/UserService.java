package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Roles;
import com.biblioteca.domain.model.UserModel;
import com.biblioteca.domain.repository.UserRepository;
import com.biblioteca.dto.request.UserRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserModel saveUser(UserRequestDTO data) {
        if (this.repository.findByEmail(data.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("email ja cadastrado");
        }
        UserModel user = new UserModel();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setDate_of_birth(data.getDate_of_birth());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
        user.setRemoved(false);
        user.setRole(Roles.READER);
        return this.repository.save(user);
    }

    public void getUseById() {
    }

    public void updateUser() {
    }

}
