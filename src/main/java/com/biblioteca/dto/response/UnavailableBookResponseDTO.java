package com.biblioteca.dto.response;

import org.springframework.http.HttpStatus;

public record UnavailableBookResponseDTO(
        String MessageError,
        HttpStatus StatusErrorCode,
        LoansResponseDTO loan
) {
}
