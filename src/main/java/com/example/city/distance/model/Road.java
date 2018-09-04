package com.example.city.distance.model;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ROAD_TO")
public class Road {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private City from;

    @EndNode
    private City to;

    @Index
    private Double distance;

    public Long getId() {
        return id;
    }

    public Road setId(Long id) {
        this.id = id;
        return this;
    }

    public City getFrom() {
        return from;
    }

    public Road setFrom(City from) {
        this.from = from;
        return this;
    }

    public City getTo() {
        return to;
    }

    public Road setTo(City to) {
        this.to = to;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Road setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
