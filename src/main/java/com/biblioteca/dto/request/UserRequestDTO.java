package com.biblioteca.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
public class UserRequestDTO {
    private String name;
    private String password;
    private String email;
    private LocalDate date_of_birth;
}
