package com.biblioteca.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@JsonPropertyOrder({"id","name","details"})
public class categoryResponseDTO {
    private UUID id;
    private String name;
    private String details;
}
