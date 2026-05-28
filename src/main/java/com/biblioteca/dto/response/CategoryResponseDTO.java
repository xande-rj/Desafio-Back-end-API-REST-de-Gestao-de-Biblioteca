package com.biblioteca.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@JsonPropertyOrder({"id","title"})
public class CategoryResponseDTO {
    private UUID id;
    private String title;
}
