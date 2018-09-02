package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.repository.RoadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoadServiceTest {

    @Autowired
    private RoadService roadService;

    @MockBean
    private RoadRepository roadRepository;

    @Test
    public void add_new_road() throws Exception {
        when(roadRepository.save(any())).thenReturn(getRoad());
        addWord();
    }

    @Test
    public void update_existing_road() throws Exception {
        when(roadRepository.findByFromIdAndToId(anyLong(), anyLong())).thenReturn(Optional.of(getRoad()));
        when(roadRepository.save(any())).thenReturn(getRoad());
        addWord();
    }

    private void addWord() {
        RoadDTO dto = getRoadDto();
        Road road = roadService.addRoad(dto);
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

    private Road getRoad(){
        return new Road()
                .setFrom(new City()
                    .setName("from"))
                .setTo(new City()
                    .setName("to"))
                .setDistance(42.);
    }


    @Test
    public void get_roads() throws Exception {
        when(roadRepository.findRoads(any(), any())).thenReturn(Collections.singletonList(getRoadInfoDto()));
        RoadInfoDTO dto = getRoadInfoDto();
        List<RoadInfoDTO> roads = roadService.getRoads("from", "to");
        assertNotNull(roads);
        assertEquals(1, roads.size());
        RoadInfoDTO road = roads.get(0);
        assertEquals(dto.getDistance(), road.getDistance());
        assertEquals(dto.getPath(), road.getPath());
    }

    @Test(expected = RoadNotFoundException.class)
    public void get_roads_empty() throws Exception {
        when(roadRepository.findRoads(any(), any())).thenReturn(Collections.emptyList());
        roadService.getRoads("from", "to");
    }


    public RoadInfoDTO getRoadInfoDto() {
        return new RoadInfoDTO()
                .setDistance(42.)
                .setPath(Arrays.asList("from", "to"));
    }
}