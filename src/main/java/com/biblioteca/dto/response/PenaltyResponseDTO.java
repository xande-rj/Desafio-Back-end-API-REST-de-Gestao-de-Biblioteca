package com.biblioteca.dto.response;

import org.springframework.http.HttpStatus;

public record PenaltyResponseDTO(
        String MessageError,
        HttpStatus StatusErrorCode,
        LoansResponseDTO loan
) {
}
