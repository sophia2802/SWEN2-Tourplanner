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

import java.util.List;

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
        TourLogEntity existingTourLog = tourLogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TourLog not found"));

        existingTourLog.setDate(tourLogDto.getDate());
        existingTourLog.setComment(tourLogDto.getComment());
        existingTourLog.setDifficulty(tourLogDto.getDifficulty());
        existingTourLog.setTotalDistance(tourLogDto.getTotalDistance());
        existingTourLog.setTotalTime(tourLogDto.getTotalTime());
        existingTourLog.setRating(tourLogDto.getRating());

        tourLogRepository.save(existingTourLog);
    }

    @Override
    public void deleteTourLog(Long id) {
        TourLogEntity tourLog = tourLogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TourLog not found"));
        tourLogRepository.delete(tourLog);
    }

    @Override
    public List<TourLogDto> getAllTourLogsByTourId(Long tourId){
        List<TourLogEntity> tourLogEntities = tourLogRepository.findByTourId(tourId);
        return tourLogMapper.mapToDto(tourLogEntities);
    }

    @Override
    public TourLogDto getTourLogById(Long id) {
        return null;
    }
}
