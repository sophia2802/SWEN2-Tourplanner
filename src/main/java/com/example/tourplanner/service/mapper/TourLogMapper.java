package com.example.tourplanner.service.mapper;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import com.example.tourplanner.service.dtos.TourDto;
import com.example.tourplanner.service.dtos.TourLogDto;
import org.springframework.stereotype.Component;

@Component
public class TourLogMapper extends AbstractMapper<TourLogEntity, TourLogDto> {
    @Override
    public TourLogDto mapToDto(TourLogEntity source) {
        return TourLogDto.builder()
                .id(source.getId())
                .tourId(source.getTour().getId())
                .date(source.getDate())
                .comment(source.getComment())
                .difficulty(source.getDifficulty())
                .totalDistance(source.getTotalDistance())
                .totalTime(source.getTotalTime())
                .rating(source.getRating())
                .build();
    }
}
