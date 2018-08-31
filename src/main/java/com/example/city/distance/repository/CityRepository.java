package com.example.city.distance.repository;

import com.example.city.distance.model.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface CityRepository extends Neo4jRepository<City, Long> {
    Optional<City> findByName(String name);
}
