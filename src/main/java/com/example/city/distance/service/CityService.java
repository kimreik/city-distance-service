package com.example.city.distance.service;

import com.example.city.distance.model.City;
import com.example.city.distance.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getNewOrExisting(String name) {
        return cityRepository.findByName(name)
                .orElse(new City().setName(name));
    }
}
