package com.biblioteca.dto.request;

import com.biblioteca.domain.enuns.Status;
import lombok.Getter;

@Getter
public class bookUpdateDTO {
    private String title;
    private String details;
    private Long pages;
    private  String category;
}
