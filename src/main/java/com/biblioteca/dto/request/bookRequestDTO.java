package com.biblioteca.dto.request;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.model.categoryModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class bookRequestDTO {
    @NotBlank(message = "The book must have a title")
    private String title;
    private String details;
    @NotBlank(message = "The book must have a pages")
    private Long pages;
    @NotBlank(message = "The book must have a author")
    private String author;
    @NotBlank(message = "The book must have a category")
    private  String category;
}
