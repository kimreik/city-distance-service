package com.example.city.distance.repository;

import com.example.city.distance.model.Road;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface RoadRepository extends Neo4jRepository<Road, Long> {
    Optional<Road> findByFromIdAndToId(Long from, Long to);
}
