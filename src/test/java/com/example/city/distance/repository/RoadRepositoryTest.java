package com.example.city.distance.repository;

import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataNeo4jTest
public class RoadRepositoryTest {
    @Autowired
    private RoadRepository roadRepository;

    private City from;
    private City to;

    @Before
    public void init() {
        from = new City()
                .setName("from");
        to = new City()
                .setName("to");
        Road road = new Road()
                .setFrom(from)
                .setTo(to);
        roadRepository.save(road);
    }

    @Test
    public void findDirectRoad() {
        List<RoadInfoDTO> roads = roadRepository.findRoads("from", "to");
        assertEquals(1, roads.size());
    }

    @Test
    public void findCompoundRoad() {
        City to2 = new City()
                .setName("to2");
        Road road = new Road()
                .setFrom(to)
                .setTo(to2);
        roadRepository.save(road);

        List<RoadInfoDTO> roads = roadRepository.findRoads("from", "to2");

        assertEquals(1, roads.size());
        assertEquals(3, roads.get(0).getPath().size());
    }

    @Test
    public void findOppositeRoad() {
        List<RoadInfoDTO> roads = roadRepository.findRoads("to", "from");
        assertEquals(1, roads.size());
    }

    @Test
    public void findByFromIdAndToId() {
        RoadList roadList = roadRepository.findDirectRoad(from.getId(), to.getId());
        assertNotNull(roadList);
    }

    @Test
    public void findByFromIdAndToIdEmpty() {
        RoadList roadList = roadRepository.findDirectRoad(41L, 42L);
        assertNull(roadList);
    }
}