package com.biblioteca.exception;

public class UnavailableBooksException extends RuntimeException {
    public UnavailableBooksException(String message) {
        super(message);
    }
}
