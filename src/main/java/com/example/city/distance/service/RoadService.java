package com.example.city.distance.service;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.exception.RoadNotFoundException;
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

    private final RoadRepository roadRepository;

    private final RoadInsertService roadInsertService;
    private final Logger logger = Logger.getLogger(RoadService.class);

    private BlockingQueue<RoadDTO> insertionQueue = new LinkedBlockingQueue<>();


    @Autowired
    public RoadService(RoadRepository roadRepository, CityService cityService, RoadInsertService roadInsertService) {
        this.roadRepository = roadRepository;
        this.roadInsertService = roadInsertService;
    }

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


    public void addRoad(RoadDTO dto) {
        insertionQueue.add(dto);
    }

    public List<RoadInfoDTO> getRoads(String from, String to) {
        List<RoadInfoDTO> roads = roadRepository.findRoads(from, to);
        if (roads.isEmpty()) {
            throw new RoadNotFoundException(from, to);
        }
        return roads;
    }
}
