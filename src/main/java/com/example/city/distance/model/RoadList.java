package com.example.city.distance.model;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
public class RoadList {

    City from;
    City to;
    List<Road> roads;

    public List<Road> getRoads() {
        return roads;
    }

    public RoadList setRoads(List<Road> roads) {
        this.roads = roads;
        return this;
    }

    public City getFrom() {
        return from;
    }

    public RoadList setFrom(City from) {
        this.from = from;
        return this;
    }

    public City getTo() {
        return to;
    }

    public RoadList setTo(City to) {
        this.to = to;
        return this;
    }
}
