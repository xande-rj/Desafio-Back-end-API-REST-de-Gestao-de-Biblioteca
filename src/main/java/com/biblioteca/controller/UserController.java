package com.biblioteca.controller;

import com.biblioteca.domain.service.UserService;
import com.biblioteca.dto.request.UserRequestDTO;
import com.biblioteca.dto.request.UserUpdateDTO;
import com.biblioteca.dto.response.UserResponseDTO;
import com.biblioteca.dto.response.UserTokenResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService userService){
        this.service = userService;
    }

    @PostMapping
    public ResponseEntity<UserTokenResponseDTO> saveUser(@RequestBody UserRequestDTO data){
        return new ResponseEntity<>(this.service.saveUser(data), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getUseById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<>(this.service.updateUser(id,userUpdateDTO),HttpStatus.OK);
    }
    @GetMapping("/{id}/loans")
    public void getLoan(){

    }
}
