package com.biblioteca.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class authorRequestDTO {
    @NotBlank(message = "The author must have a name.")
    private String name;
    @NotBlank(message = "The author must have a datails.")
    private String details;
}
