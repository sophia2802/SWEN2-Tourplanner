package com.example.tourplanner.api;


import com.example.tourplanner.service.TourLogService;
import com.example.tourplanner.service.TourService;
import com.example.tourplanner.service.dtos.TourDto;
import com.example.tourplanner.service.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tourlog")

public class TourLogApi {

    @Autowired
    private TourLogService tourLogService;

    @PostMapping
    public void createTourLog(@RequestBody TourLogDto tourLogDto) {
        tourLogService.createTourLog(tourLogDto);
    }
}
