package com.example.city.distance.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoadDTO {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    @Min(0)
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
