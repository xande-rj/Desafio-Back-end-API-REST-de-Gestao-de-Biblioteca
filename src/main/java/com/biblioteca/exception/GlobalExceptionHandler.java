package com.biblioteca.exception;

import com.biblioteca.domain.model.LoanModel;
import com.biblioteca.dto.response.LoansResponseDTO;
import com.biblioteca.dto.response.UnavailableBookResponseDTO;
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
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


    @ExceptionHandler(LimitBooksException.class)
    public ResponseEntity<String> LimitBooks(LimitBooksException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UnavailableBooksException.class)
    public ResponseEntity<String> UnavailableBooks(UnavailableBooksException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    @ExceptionHandler(PenaltyLoanException.class)
    public ResponseEntity<UnavailableBookResponseDTO> PenaltyBooks(PenaltyLoanException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UnavailableBookResponseDTO(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        ex.getLoan()
                        )

                );



    }
}
