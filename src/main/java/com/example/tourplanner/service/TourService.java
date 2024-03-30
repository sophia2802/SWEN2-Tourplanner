package com.example.tourplanner.service;

import com.example.tourplanner.service.dtos.TourDto;

import java.util.List;

public interface TourService {
    void createTour(TourDto tourDto);
    void updateTour(Long id, TourDto tourDto);
    void deleteTour(Long id);
    TourDto getTourById(Long id);
    List<TourDto> getAllTours();
    List<TourDto> searchTours(String keyword);

}
