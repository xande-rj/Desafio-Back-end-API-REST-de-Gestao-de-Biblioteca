package com.biblioteca.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;



@Getter
public class bookRequestDTO {
    @NotBlank(message = "The book must have a title")
    private String title;
    private String details;
    @Positive(message = "The price must have positive")
    @NotNull(message = "There must be a price.")
    private Long pages;
    @NotBlank(message = "The book must have a author")
    private String author;
    @NotBlank(message = "The book must have a category")
    private  String category;
}
