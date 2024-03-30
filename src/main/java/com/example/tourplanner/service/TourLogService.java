package com.example.tourplanner.service;

import com.example.tourplanner.service.dtos.TourLogDto;

public interface TourLogService {

    void createTourLog(TourLogDto tourLogDto);
    void updateTourLog(Long id, TourLogDto tourLogDto);
    void deleteTourLog(Long id);
    TourLogDto getTourLogById(Long id);
}
