package com.biblioteca.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRequestDTO {
    @NotNull(message = "The user needs a name.")
    private String name;
    @NotNull(message = "User needs password.")
    private String password;
    @NotNull(message = "The user needs an email address.")
    private String email;
    @NotNull(message = "The user needs a date of birth.")
    private LocalDate date_of_birth;
    private String roles;
}
