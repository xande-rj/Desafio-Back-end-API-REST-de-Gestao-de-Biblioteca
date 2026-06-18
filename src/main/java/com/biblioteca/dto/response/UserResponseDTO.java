package com.biblioteca.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private String name;
    private String email;
    private boolean removed;
    private LocalDate date_of_birth;
    private List<LoansResponseDTO> loans;
    private List<LoansResponseDTO> historical;
    private LocalDateTime created_at;
}
