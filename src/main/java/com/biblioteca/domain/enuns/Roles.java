package com.biblioteca.domain.enuns;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Roles de usuario")
public enum Roles {
    ADMIN,
    ATTENDANT,
    READER
}
