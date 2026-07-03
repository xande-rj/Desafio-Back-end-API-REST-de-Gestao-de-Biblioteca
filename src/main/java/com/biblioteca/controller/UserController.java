package com.biblioteca.controller;

import com.biblioteca.domain.service.UserService;
import com.biblioteca.dto.request.UserAuthRequestDTO;
import com.biblioteca.dto.request.UserRequestDTO;
import com.biblioteca.dto.request.UserUpdateDTO;
import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.dto.response.UserResponseDTO;
import com.biblioteca.dto.response.UserTokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(
        name = "Users",
        description = "User-related operations"
)
public class UserController {
    private final UserService service;
    public UserController(UserService userService){
        this.service = userService;
    }

    @PostMapping("/auth/login")
    @Operation(
            summary = "User authentication",
            description = "Verifies a user's credentials."
    )
    public ResponseEntity<UserTokenResponseDTO> authUser(@RequestBody UserAuthRequestDTO data){
        return new ResponseEntity<>(this.service.authUser(data), HttpStatus.OK);
    }

    @PostMapping("/users")
    @Operation(
            summary = "Creates a new user",
            description = "Creates a new user in the database."
    )
    public ResponseEntity<UserTokenResponseDTO> saveUser(@RequestBody UserRequestDTO data){
        return new ResponseEntity<>(this.service.saveUser(data), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "Returns a user",
            description = "Returns a user by ID."
    )
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getUseById(id),HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    @Operation(
            summary = "Updates a user",
            description = "Updates a user's data."
    )
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<>(this.service.updateUser(id,userUpdateDTO),HttpStatus.OK);
    }

    @GetMapping("/users/{id}/loans")
    @Operation(
            summary = "Returns the loans",
            description = "returns all loans for a user."
    )
    public ResponseEntity<List<LoansResponseDTO>> getLoan(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.service.historicalLoans(id));

    }
}
