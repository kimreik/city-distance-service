package com.example.city.distance.exception;

import org.springframework.validation.Errors;


public class CustomMethodArgumentNotValidException extends RuntimeException {
    private Errors errors;

    public CustomMethodArgumentNotValidException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
