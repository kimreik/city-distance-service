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
    private Float distance;

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

    public Float getDistance() {
        return distance;
    }

    public RoadDTO setDistance(Float distance) {
        this.distance = distance;
        return this;
    }
}
