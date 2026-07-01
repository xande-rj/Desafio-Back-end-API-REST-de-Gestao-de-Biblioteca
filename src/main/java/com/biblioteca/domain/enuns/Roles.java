package com.biblioteca.domain.enuns;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Roles de usuario")
public enum Roles {
    ADMIN("Admin"),
    ATTENDANT("Attendant"),
    READER("Reader");

    private final String description;

    Roles(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
