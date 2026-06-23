package com.biblioteca.dto.response;

import org.springframework.http.HttpStatus;

public record ErroResponseDTO(
        String MessageError,
        HttpStatus StatusCode
) {
}
