package com.example.city.distance.model;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class City {

    @Id
    @GeneratedValue
    private Long id;
    @Index(unique = true)
    private String name;

    @Relationship(type = "HAS_ROAD_TO", direction = Relationship.UNDIRECTED)
    private List<Road> roads = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public City setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public City setRoads(List<Road> roads) {
        this.roads = roads;
        return this;
    }
}
