package com.biblioteca.exception;

import com.biblioteca.dto.response.LoansResponseDTO;

public class PenaltyLoanException extends RuntimeException {
    private LoansResponseDTO loansResponseDTO;
    public PenaltyLoanException(String message, LoansResponseDTO loansResponseDTO) {

        super(message);
this.loansResponseDTO = loansResponseDTO;
    }

    public LoansResponseDTO getLoan(){
        return loansResponseDTO;
    }
}
