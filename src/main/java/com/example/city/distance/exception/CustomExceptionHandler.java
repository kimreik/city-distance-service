package com.example.city.distance.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RoadNotFoundException.class)
    public ResponseEntity handleRoadNotFoundException(RoadNotFoundException exception) {
        HashMap<String, String> responseBody = new HashMap<>();
        responseBody.put("error", exception.getMessage());
        return ok().body(responseBody);
    }

    @ExceptionHandler(CustomMethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(CustomMethodArgumentNotValidException exception) {
        Map<String, Map<String, String>> responseBody = new HashMap<>();
        responseBody.put("error", convertErrors(exception.getErrors()));
        return badRequest().body(responseBody);
    }

    private Map<String, String> convertErrors(Errors errors) {
        return errors.getAllErrors().stream().collect(Collectors.toMap(ObjectError::getCode, this::getErrorMessage));
    }

    private String getErrorMessage(ObjectError error) {
        return Optional.ofNullable(error.getDefaultMessage()).orElse("invalid field");
    }
}
