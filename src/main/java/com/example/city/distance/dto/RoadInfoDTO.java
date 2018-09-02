package com.example.city.distance.dto;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

@QueryResult
public class RoadInfoDTO {
    private List<String> path = new ArrayList<>();
    private Double distance;

    public List<String> getPath() {
        return path;
    }

    public RoadInfoDTO setPath(List<String> path) {
        this.path = path;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public RoadInfoDTO setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
