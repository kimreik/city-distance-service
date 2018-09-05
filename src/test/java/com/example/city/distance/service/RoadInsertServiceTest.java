package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import com.example.city.distance.repository.RoadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoadInsertServiceTest {

    @Autowired
    private RoadInsertService roadInsertService;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    public void insert_new_road() {
        when(roadRepository.save(any())).thenReturn(getRoad());
        insertRoad();
    }

    @Test
    public void update_existing_road() {
        when(roadRepository.findDirectRoad(any(), any())).thenReturn(getRoadList());
        when(roadRepository.save(any())).thenReturn(getRoad());
        insertRoad();
    }

    private void insertRoad() {
        RoadDTO dto = getRoadDto();
        Road road = roadInsertService.insertRoad(dto);
        assertEquals(dto.getDistance(), road.getDistance());
        assertEquals(dto.getFrom(), road.getFrom().getName());
        assertEquals(dto.getTo(), road.getTo().getName());
    }


    private RoadDTO getRoadDto() {
        return new RoadDTO()
                .setFrom("from")
                .setTo("to")
                .setDistance(42.);
    }

    private RoadList getRoadList() {
        return new RoadList().setRoads(Collections.singletonList(getRoad()));
    }

    private Road getRoad() {
        return new Road()
                .setFrom(new City()
                        .setName("from"))
                .setTo(new City()
                        .setName("to"))
                .setDistance(42.);
    }

}
