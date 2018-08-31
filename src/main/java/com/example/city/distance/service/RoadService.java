package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.repository.RoadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoadService {

    private final RoadRepository roadRepository;

    private final CityService cityService;

    public RoadService(RoadRepository roadRepository, CityService cityService) {
        this.roadRepository = roadRepository;
        this.cityService = cityService;
    }

    @Transactional
    public void addRoad(RoadDTO dto) {
        City from = cityService.getNewOrExisting(dto.getFrom());
        City to = cityService.getNewOrExisting(dto.getTo());

        Road road = getRoadBetweenTwoCities(from, to)
                .orElse(new Road()
                        .setFrom(from)
                        .setTo(to))
                .setDistance(dto.getDistance());

        roadRepository.save(road);

        //TODO update with custom query?
        from.getRoads().add(road);
        to.getRoads().add(road);
        cityService.save(from);
        cityService.save(to);
    }

    private Optional<Road> getRoadBetweenTwoCities(City first, City second){
        //TODO find a way to do this via db query
        ArrayList<Road> roads = new ArrayList<>(first.getRoads());
        roads.retainAll(second.getRoads());
        return Optional.ofNullable(roads.size()>0?roads.get(0):null);
    }

}
