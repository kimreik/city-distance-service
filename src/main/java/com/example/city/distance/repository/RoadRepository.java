package com.example.city.distance.repository;

import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoadRepository extends Neo4jRepository<Road, Long> {

    @Query("MATCH (from: City)-[r:HAS_ROAD_TO]-(to: City)\n" +
            "WHERE id(from) = {from} AND id(to) = {to}\n" +
            "RETURN from, collect(r) as roads, to")
    RoadList findDirectRoad(@Param("from") Long from, @Param("to") Long to);

    @Query("MATCH (from: City {name:{from}}), (to: City {name:{to}}) , \n" +
            "p = (from)-[rels:HAS_ROAD_TO*]-(to)\n" +
            "WITH REDUCE(dist = 0, rel in rels | dist + rel.distance) AS distance, p\n" +
            "RETURN extract(n IN nodes(p)| n.name) as path, distance")
    List<RoadInfoDTO> findRoads(@Param("from") String from, @Param("to") String to);
}

