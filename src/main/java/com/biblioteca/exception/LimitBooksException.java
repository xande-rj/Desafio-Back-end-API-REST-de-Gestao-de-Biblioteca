package com.biblioteca.exception;

public class LimitBooksException extends RuntimeException {
    public LimitBooksException(String message) {
        super(message);
    }
}
