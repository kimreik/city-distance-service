package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import com.example.city.distance.repository.RoadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoadInsertService {

    private final CityService cityService;

    private final RoadRepository roadRepository;

    public RoadInsertService(CityService cityService, RoadRepository roadRepository) {
        this.cityService = cityService;
        this.roadRepository = roadRepository;
    }

    @Transactional
    public Road insertRoad(RoadDTO dto) {
        City from = cityService.getNewOrExisting(dto.getFrom());
        City to = cityService.getNewOrExisting(dto.getTo());
        Road road = getDirectRoad(from, to)
                .setDistance(dto.getDistance());
        return roadRepository.save(road);
    }

    private Road getDirectRoad(City from, City to) {
        RoadList roadList = roadRepository.findDirectRoad(from.getId(), to.getId());
        if (roadList == null) {
            return new Road().setFrom(from).setTo(to);
        }
        return roadList.getRoads().get(0);
    }
}
