package com.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class categoryRequestDTO {
    @NotBlank(message = "The category must have a title")
    private String title;
}
