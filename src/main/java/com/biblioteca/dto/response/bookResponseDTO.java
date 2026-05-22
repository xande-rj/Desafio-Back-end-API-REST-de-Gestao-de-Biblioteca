package com.biblioteca.dto.response;

import com.biblioteca.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id","title","details","pages","status","created_at","author","category"})
public class bookResponseDTO {
    private Long id;
    private String title;
    private String details;
    private Long pages;
    private Status status;
    private LocalDateTime created_at;
    private authorResponseDTO author;
    private  categoryResponseDTO category;
}
