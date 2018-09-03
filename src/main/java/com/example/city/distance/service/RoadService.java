package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import com.example.city.distance.repository.RoadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadService {

    private final RoadRepository roadRepository;

    private final CityService cityService;

    public RoadService(RoadRepository roadRepository, CityService cityService) {
        this.roadRepository = roadRepository;
        this.cityService = cityService;
    }

    public Road addRoad(RoadDTO dto) {
        City from = cityService.getNewOrExisting(dto.getFrom());
        City to = cityService.getNewOrExisting(dto.getTo());

        Road road = getRoadBetweenTwoCities(from, to)
                .setDistance(dto.getDistance());

        return roadRepository.save(road);
    }

    private Road getRoadBetweenTwoCities(City from, City to) {
        RoadList roadList = roadRepository.findDirectRoad(from.getId(), to.getId());
        if (roadList == null) {
            return new Road().setFrom(from).setTo(to);
        }
        return roadList.getRoads().get(0);
    }

    public List<RoadInfoDTO> getRoads(String from, String to) {
        List<RoadInfoDTO> roads = roadRepository.findRoads(from, to);
        if (roads.isEmpty()) {
            throw new RoadNotFoundException(from, to);
        }
        return roads;
    }
}
