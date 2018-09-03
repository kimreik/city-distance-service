package com.example.city.distance.controller;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.service.RoadService;
import com.example.city.distance.validator.RoadDTOValidator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roads")
public class RoadController {

    private final RoadService roadService;

    private final RoadDTOValidator roadDTOValidator;

    public RoadController(RoadService roadService, RoadDTOValidator roadDTOValidator) {
        this.roadService = roadService;
        this.roadDTOValidator = roadDTOValidator;
    }

    @PostMapping
    public void addRoad(@RequestBody RoadDTO dto) {
        roadDTOValidator.validate(dto);
        roadService.addRoad(dto);
    }

    @GetMapping
    public List<RoadInfoDTO> getRoads(@RequestParam String from, @RequestParam String to){
        return roadService.getRoads(from, to);
    }
}
