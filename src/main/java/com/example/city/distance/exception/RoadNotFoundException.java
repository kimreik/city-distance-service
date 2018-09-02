package com.example.city.distance.exception;

public class RoadNotFoundException extends RuntimeException {

    public RoadNotFoundException(String from, String to) {
        super("Road from '"+from+"' to '"+to+"' not found");
    }
}
