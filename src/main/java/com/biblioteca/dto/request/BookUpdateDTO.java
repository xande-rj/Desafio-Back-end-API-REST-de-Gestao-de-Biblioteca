package com.biblioteca.dto.request;

import lombok.Getter;

@Getter
public class BookUpdateDTO {
    private String title;
    private String details;
    private Long pages;
    private  String category;
}
