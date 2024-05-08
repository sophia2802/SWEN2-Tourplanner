package com.example.tourplanner.api;


import com.example.tourplanner.service.TourLogService;
import com.example.tourplanner.service.dtos.TourLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "tourlog")

public class TourLogApi {

    @Autowired
    private TourLogService tourLogService;

    @PostMapping
    public void createTourLog(@RequestBody TourLogDto tourLogDto) {
        tourLogService.createTourLog(tourLogDto);
    }

    @PutMapping("/{id}")
    public void updateTourLog(@PathVariable Long id, @RequestBody TourLogDto tourLogDto) {
        tourLogService.updateTourLog(id, tourLogDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourLogService.deleteTourLog(id);
    }

    @GetMapping("/{tourId}/tourlogs")
    public List<TourLogDto> getTourLogsByTour(@PathVariable Long tourId) {
        return tourLogService.getAllTourLogsByTourId(tourId);
    }
}
