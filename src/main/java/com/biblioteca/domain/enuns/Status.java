package com.biblioteca.domain.enuns;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status de disponibilidade")
public enum Status {
    AVAILABLE,
    UNAVAILABLE
}
