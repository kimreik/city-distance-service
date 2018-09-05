package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoadInsertService {

    @Autowired
    private CityService cityService;

    @Autowired
    private RoadService roadService;

    @Transactional
    public Road insertRoad(RoadDTO dto) {
        City from = cityService.getNewOrExisting(dto.getFrom());
        City to = cityService.getNewOrExisting(dto.getTo());
        Road road = roadService.getDirectRoad(from, to)
                .setDistance(dto.getDistance());
        return roadService.save(road);
    }

}
