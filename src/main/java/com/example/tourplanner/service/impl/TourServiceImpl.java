package com.example.tourplanner.service.impl;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.service.TourService;
import com.example.tourplanner.service.dtos.TourDto;
import com.example.tourplanner.service.mapper.TourMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TourServiceImpl implements TourService {

    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private TourRepository tourRepository;

    @Override
    public void createTour(TourDto tourDto){
        TourEntity entity = TourEntity.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .arrival(tourDto.getArrival())
                .departure(tourDto.getDeparture())
                .transportType(tourDto.getTransportType())
                .distance(tourDto.getDistance())
                .estimatedTime(tourDto.getEstimatedTime())
                .routeImage(tourDto.getRouteImage())
                .build();

        tourRepository.save(entity);
    }

    @Override
    public void updateTour(Long id, TourDto tourDto){
        TourEntity existingTour = tourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tour not found"));

        existingTour.setName(tourDto.getName());
        existingTour.setDescription(tourDto.getDescription());
        existingTour.setArrival(tourDto.getArrival());
        existingTour.setDeparture(tourDto.getDeparture());
        existingTour.setTransportType(tourDto.getTransportType());
        existingTour.setDistance(tourDto.getDistance());
        existingTour.setEstimatedTime(tourDto.getEstimatedTime());
        existingTour.setRouteImage(tourDto.getRouteImage());

        tourRepository.save(existingTour);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tour not found"));
        tourRepository.deleteById(id);
    }

    @Override
    public TourDto getTourById(Long id){
        TourEntity tourEntity = tourRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tour not found"));
        return tourMapper.mapToDto(tourEntity);
    }

    @Override
    public List<TourDto> getAllTours(){
        return tourMapper.mapToDto(tourRepository.findAll());
    }

    @Override
    public List<TourDto> searchTours(String keyword) {
        List<TourEntity> tours = tourRepository.findByNameContainingIgnoreCase(keyword);
        return tourMapper.mapToDto(tours);
    }
}
