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
    public void insertTour(@RequestBody TourDto tourDto) {
        tourService.createTour(tourDto);
    }

    @PutMapping("/{id}")
    public void updateTour(@PathVariable Long id, @RequestBody TourDto tourDto) {
        tourService.updateTour(id, tourDto);
    }

    @GetMapping("/id/{id}")
    public TourDto getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }

    @GetMapping
    public List<TourDto> getAllTours() {
        return tourService.getAllTours();
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }

    @GetMapping("/search")
    public List<TourDto> searchTours(@RequestParam String keyword) {
        return tourService.searchTours(keyword);
    }
}
