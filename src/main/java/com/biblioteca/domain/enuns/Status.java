package com.biblioteca.domain.enuns;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status de disponibilidade")
public enum Status {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private final String description;

    Status(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
}
