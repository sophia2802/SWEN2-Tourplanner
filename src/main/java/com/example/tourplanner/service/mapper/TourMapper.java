package com.example.tourplanner.service.mapper;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.service.dtos.TourDto;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<TourEntity, TourDto> {

    @Override
    public TourDto mapToDto(TourEntity source) {
        return TourDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .departure(source.getDeparture())
                .arrival(source.getArrival())
                .transportType(source.getTransportType())
                .distance(source.getDistance())
                .estimatedTime(source.getEstimatedTime())
                .routeImage(source.getRouteImage())
                .build();
    }
}
