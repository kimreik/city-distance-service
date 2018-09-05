package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
import com.example.city.distance.repository.RoadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoadServiceTest {

    @SpyBean
    private RoadService roadService;

    @MockBean
    private RoadRepository roadRepository;

    @MockBean
    private RoadInsertService roadInsertService;

    @Test
    public void add() {
        doNothing().when(roadService).init();
        RoadDTO dto = getRoadDto();
        assertTrue(roadService.addRoad(dto));
    }

    private RoadDTO getRoadDto() {
        return new RoadDTO()
                .setFrom("from")
                .setTo("to")
                .setDistance(42.);
    }

    @Test
    public void get_roads() {
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
    public void get_roads_empty() {
        when(roadRepository.findRoads(any(), any())).thenReturn(Collections.emptyList());
        roadService.getRoads("from", "to");
    }


    public RoadInfoDTO getRoadInfoDto() {
        return new RoadInfoDTO()
                .setDistance(42.)
                .setPath(Arrays.asList("from", "to"));
    }
}