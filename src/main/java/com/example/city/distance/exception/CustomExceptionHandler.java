package com.example.city.distance.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

import static org.springframework.http.ResponseEntity.ok;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RoadNotFoundException.class)
    public ResponseEntity handleRoadNotFoundException(RoadNotFoundException exception){
        HashMap<String, String> responseBody = new HashMap<>();
        responseBody.put("error", exception.getMessage());
        return ok().body(responseBody);
    }
}
