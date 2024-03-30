package com.example.tourplanner.service.impl;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import com.example.tourplanner.persistence.repositories.TourLogRepository;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.service.TourLogService;
import com.example.tourplanner.service.dtos.TourLogDto;
import com.example.tourplanner.service.mapper.TourLogMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourLogServiceImpl implements TourLogService {

    @Autowired
    TourLogRepository tourLogRepository;

    @Autowired
    TourLogMapper tourLogMapper;

    @Autowired
    TourRepository tourRepository;

    @Override
    public void createTourLog(TourLogDto tourLogDto) {
        TourEntity relatedTour = tourRepository.findById(tourLogDto.getTourId()).orElseThrow(() -> new EntityNotFoundException("Tour not found"));

        TourLogEntity tourLogEntity = TourLogEntity.builder()
                .tour(relatedTour)
                .date(tourLogDto.getDate())
                .comment(tourLogDto.getComment())
                .difficulty(tourLogDto.getDifficulty())
                .totalDistance(tourLogDto.getTotalDistance())
                .totalTime(tourLogDto.getTotalTime())
                .rating(tourLogDto.getRating())
                .build();

        tourLogRepository.save(tourLogEntity);
    }

    @Override
    public void updateTourLog(Long id, TourLogDto tourLogDto) {

    }

    @Override
    public void deleteTourLog(Long id) {

    }

    @Override
    public TourLogDto getTourLogById(Long id) {
        return null;
    }
}
