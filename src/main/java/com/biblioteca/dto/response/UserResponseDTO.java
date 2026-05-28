package com.biblioteca.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class UserResponseDTO {
    private String name;
    private String email;
    private boolean removed;
    private LocalDate date_of_birth;
    private LocalDateTime created_at;
}
