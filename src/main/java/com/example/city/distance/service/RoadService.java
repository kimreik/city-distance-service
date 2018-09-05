package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
import com.example.city.distance.model.City;
import com.example.city.distance.model.Road;
import com.example.city.distance.model.RoadList;
import com.example.city.distance.repository.RoadRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private RoadInsertService roadInsertService;

    private final Logger logger = Logger.getLogger(RoadService.class);

    private BlockingQueue<RoadDTO> insertionQueue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init() {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while (true) {
                    logger.info("ready for next, queue size:" + insertionQueue.size());
                    roadInsertService.insertRoad(insertionQueue.take());
                }
            } catch (Exception e) {
                logger.error("insertion queue processing error", e);
            }
        });
    }


    public boolean addRoad(RoadDTO dto) {
        return insertionQueue.add(dto);
    }

    public List<RoadInfoDTO> getRoads(String from, String to) {
        List<RoadInfoDTO> roads = roadRepository.findRoads(from, to);
        if (roads.isEmpty()) {
            throw new RoadNotFoundException(from, to);
        }
        return roads;
    }

    public Road getDirectRoad(City from, City to) {
        RoadList roadList = roadRepository.findDirectRoad(from.getId(), to.getId());
        if (roadList == null) {
            return new Road().setFrom(from).setTo(to);
        }
        return roadList.getRoads().get(0);
    }

    public Road save(Road road) {
        return roadRepository.save(road);
    }
}
