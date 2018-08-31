package com.example.city.distance.controller;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.service.RoadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/road")
public class RoadController {

    private final RoadService roadService;

    public RoadController(RoadService roadService) {
        this.roadService = roadService;
    }

    @PostMapping
    public void addRoad(@RequestBody @Valid RoadDTO dto) {
        roadService.addRoad(dto);
    }
}
