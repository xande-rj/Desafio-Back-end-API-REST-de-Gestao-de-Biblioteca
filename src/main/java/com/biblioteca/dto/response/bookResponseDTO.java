package com.biblioteca.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id","title","details","pages","created_at","author","categorys"})
public class bookResponseDTO {
    private Long id;
    private String title;
    private String details;
    private Long pages;
    private LocalDateTime created_at;
    private authorResponseDTO author;
    private  categoryResponseDTO category;
}
