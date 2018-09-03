package com.example.city.distance.dto;

public class RoadDTO {
    private String from;
    private String to;
    private Double distance;

    public String getFrom() {
        return from;
    }

    public RoadDTO setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public RoadDTO setTo(String to) {
        this.to = to;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public RoadDTO setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
