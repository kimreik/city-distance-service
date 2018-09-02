package com.example.city.distance.controller;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.dto.RoadInfoDTO;
import com.example.city.distance.service.RoadService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roads")
public class RoadController {

    private final RoadService roadService;

    public RoadController(RoadService roadService) {
        this.roadService = roadService;
    }

    @PostMapping
    public void addRoad(@RequestBody @Valid RoadDTO dto) {
        roadService.addRoad(dto);
    }

    @GetMapping
    public List<RoadInfoDTO> getRoads(@RequestParam String from, @RequestParam String to){
        return roadService.getRoads(from, to);
    }
}
