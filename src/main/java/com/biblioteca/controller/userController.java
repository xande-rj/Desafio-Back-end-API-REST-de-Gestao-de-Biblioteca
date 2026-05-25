package com.biblioteca.controller;

import com.biblioteca.domain.service.userService;
import com.biblioteca.dto.request.UserRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class userController {
    private final userService service;
    public userController(userService userService){
        this.service = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRequestDTO data){
        return new ResponseEntity<>(this.service.saveUser(data), HttpStatus.CREATED);
    }

}
