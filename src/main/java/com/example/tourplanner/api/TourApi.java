package com.example.tourplanner.api;


import com.example.tourplanner.service.TourService;
import com.example.tourplanner.service.dtos.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tour")


public class TourApi {

    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity<TourDto> createTour(@RequestBody TourDto tourDto) {
        tourService.createTour(tourDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
