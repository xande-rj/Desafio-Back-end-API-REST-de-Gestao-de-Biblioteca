package com.biblioteca.exception;


import com.biblioteca.dto.response.ErroResponseDTO;
import com.biblioteca.dto.response.PenaltyResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map <String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(),error.getDefaultMessage());
        });

return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResponseDTO> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErroResponseDTO(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND
                        )
                );
    }


    @ExceptionHandler(LimitBooksException.class)
    public ResponseEntity<ErroResponseDTO> LimitBooks(LimitBooksException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErroResponseDTO(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST
                ));
    }

    @ExceptionHandler(UnavailableBooksException.class)
    public ResponseEntity<ErroResponseDTO> UnavailableBooks(UnavailableBooksException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErroResponseDTO(
                                ex.getMessage(),
                                HttpStatus.BAD_REQUEST
                        )
                );
    }


    @ExceptionHandler(PenaltyLoanException.class)
    public ResponseEntity<PenaltyResponseDTO> PenaltyBooks(PenaltyLoanException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new PenaltyResponseDTO(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ex.getLoan()
                        )

                );



    }
}
