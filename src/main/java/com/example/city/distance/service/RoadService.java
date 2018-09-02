package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.repository.RoadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .orElse(new Road()
                        .setFrom(from)
                        .setTo(to))
                .setDistance(dto.getDistance());

        //TODO update with custom query?
        from.getRoads().add(road);
        to.getRoads().add(road);
        cityService.save(from);
        cityService.save(to);

        return roadRepository.save(road);
    }

    private Optional<Road> getRoadBetweenTwoCities(City first, City second){
        //TODO find a way to do this via db query
        ArrayList<Road> roads = new ArrayList<>(first.getRoads());
        roads.retainAll(second.getRoads());
        return Optional.ofNullable(roads.size()>0?roads.get(0):null);
    }

    public List<RoadInfoDTO> getRoads(String from, String to) {
        List<RoadInfoDTO> roads = roadRepository.findRoads(from, to);
        if(roads.isEmpty()){
            throw new RoadNotFoundException(from, to);
        }
        return roads;
    }
}
