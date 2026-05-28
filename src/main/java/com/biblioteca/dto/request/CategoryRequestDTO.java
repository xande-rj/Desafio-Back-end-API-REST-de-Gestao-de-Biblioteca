package com.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "The category must have a title")
    private String title;
}
