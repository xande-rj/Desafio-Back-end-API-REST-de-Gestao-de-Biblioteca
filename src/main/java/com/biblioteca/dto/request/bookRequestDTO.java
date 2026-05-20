package com.biblioteca.dto.request;

import com.biblioteca.domain.model.authorModel;
import com.biblioteca.domain.model.categoryModel;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class bookRequestDTO {
    private String title;
    private String details;
    private Long pages;
    private String author;
    private  String categorys;

}
