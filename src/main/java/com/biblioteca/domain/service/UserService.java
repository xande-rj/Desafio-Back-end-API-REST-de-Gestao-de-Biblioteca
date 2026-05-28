package com.biblioteca.domain.service;

import com.biblioteca.domain.enuns.Roles;
import com.biblioteca.domain.model.userModel;
import com.biblioteca.domain.repository.userRepository;
import com.biblioteca.dto.request.UserRequestDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class userService {
    private final userRepository repository;

    public userService(userRepository userRepository) {
        this.repository = userRepository;
    }

    public userModel saveUser(UserRequestDTO data) {
        if (this.repository.findByEmail(data.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("email ja cadastrado");
        }
        userModel user = new userModel();
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
