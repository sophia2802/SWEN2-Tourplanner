package com.example.tourplanner.service.impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.service.dtos.TourDto;
import com.example.tourplanner.service.mapper.TourMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

public class TourServiceImplTest {
    @Mock
    private TourRepository tourRepository;

    @Mock
    private TourMapper tourMapper;

    @InjectMocks
    private TourServiceImpl tourService;

    private TourDto mockTourDto;
    private TourEntity mockTourEntity;
    private final Long tourId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockTourDto = new TourDto();
        mockTourDto.setName("Test Tour");
        mockTourDto.setDescription("Test Description");
        mockTourDto.setDeparture("Test Abfahrtsort");
        mockTourDto.setArrival("Test Ankunftsort");
        mockTourDto.setDistance(60.5);
        mockTourDto.setEstimatedTime("3 Stunden");
        mockTourDto.setTransportType("Fahrrad");
        mockTourDto.setRouteImage("Test.jpg");

        mockTourEntity = new TourEntity();
        mockTourEntity.setName(mockTourDto.getName());
        mockTourEntity.setDescription(mockTourDto.getDescription());
        mockTourEntity.setDeparture(mockTourDto.getDeparture());
        mockTourEntity.setArrival(mockTourDto.getArrival());
        mockTourEntity.setDistance(mockTourDto.getDistance());
        mockTourEntity.setEstimatedTime(mockTourDto.getEstimatedTime());
        mockTourEntity.setTransportType(mockTourDto.getTransportType());
        mockTourEntity.setRouteImage(mockTourDto.getRouteImage());
    }

    @Test
    void createTour() {
        when(tourRepository.save(any(TourEntity.class))).thenReturn(mockTourEntity);    //defines expecting save behavior --> if tour entity is called, return mockTourEntity
        tourService.createTour(mockTourDto);
        verify(tourRepository).save(any(TourEntity.class));
    }

    @Test
    void deleteTour() {
        mockTourDto.setId(tourId);
        mockTourEntity.setId(tourId);
        when(tourRepository.save(any(TourEntity.class))).thenReturn(mockTourEntity);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(mockTourEntity));
        tourService.createTour(mockTourDto);
        tourService.deleteTour(tourId);
        verify(tourRepository).deleteById(eq(tourId));
    }

    @Test
    void getTourById() {
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(mockTourEntity));
        when(tourMapper.mapToDto(any(TourEntity.class))).thenReturn(mockTourDto);

        TourDto result = tourService.getTourById(tourId);

        assertNotNull(result);
        assertEquals(mockTourDto, result);
        verify(tourRepository).findById(tourId);
    }

    @Test
    void updateTour() {
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(mockTourEntity));
        when(tourRepository.save(any(TourEntity.class))).thenReturn(mockTourEntity);

        tourService.updateTour(tourId, mockTourDto);

        verify(tourRepository).save(mockTourEntity);
    }

    @Test
    void updateTour_TourNotFound() {
        when(tourRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {   //assertThrows checks if it throws an exception
            tourService.updateTour(1L, new TourDto());
        });

        String expectedMessage = "Tour not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(tourRepository, never()).save(any(TourEntity.class));
    }
}
