package com.example.tourplanner.service.impl;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import com.example.tourplanner.persistence.repositories.TourLogRepository;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.service.dtos.TourLogDto;
import com.example.tourplanner.service.mapper.TourLogMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class TourLogServiceImplTest {

    @Mock
    private TourLogRepository tourLogRepository;

    @Mock
    private TourLogMapper tourLogMapper;

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourLogServiceImpl tourLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTourLog() {
        TourLogDto dto = new TourLogDto();
        dto.setTourId(1L);
        TourEntity tour = new TourEntity();
        when(tourRepository.findById(dto.getTourId())).thenReturn(Optional.of(tour));

        tourLogService.createTourLog(dto);

        verify(tourLogRepository).save(any(TourLogEntity.class));
    }

    @Test
    void createTourLog_NotFound() {
        TourLogDto dto = new TourLogDto();
        dto.setTourId(1L);
        when(tourRepository.findById(dto.getTourId())).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> tourLogService.createTourLog(dto))
                .withMessage("Tour not found");
    }

    @Test
    void updateTourLog() {
        Long id = 1L;
        TourLogDto dto = new TourLogDto();
        TourLogEntity logEntity = new TourLogEntity();
        when(tourLogRepository.findById(id)).thenReturn(Optional.of(logEntity));

        tourLogService.updateTourLog(id, dto);

        verify(tourLogRepository).save(logEntity);
    }

    @Test
    void updateTourLog_NotFound() {
        Long id = 1L;
        TourLogDto dto = new TourLogDto();
        when(tourLogRepository.findById(id)).thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> tourLogService.updateTourLog(id, dto))
                .withMessage("TourLog not found");
    }

    @Test
    void deleteTourLog() {
        Long id = 1L;
        TourLogEntity log = new TourLogEntity();
        when(tourLogRepository.findById(id)).thenReturn(Optional.of(log));

        tourLogService.deleteTourLog(id);

        verify(tourLogRepository).delete(log);
    }

    @Test
    void getAllTourLogsByTourId() {
        Long tourId = 1L;
        List<TourLogEntity> logs = Arrays.asList(new TourLogEntity());
        when(tourLogRepository.findByTourId(tourId)).thenReturn(logs);
        when(tourLogMapper.mapToDto(logs)).thenReturn(Arrays.asList(new TourLogDto()));

        List<TourLogDto> result = tourLogService.getAllTourLogsByTourId(tourId);

        assertThat(result).isNotEmpty();
        verify(tourLogMapper).mapToDto(logs);
    }

    @Test
    void getAllTourLogsByTourId_LogsNotFound() {
        Long tourId = 1L;
        when(tourLogRepository.findByTourId(tourId)).thenReturn(Arrays.asList());
        when(tourLogMapper.mapToDto(anyList())).thenReturn(Arrays.asList());

        List<TourLogDto> result = tourLogService.getAllTourLogsByTourId(tourId);

        assertThat(result).isEmpty();
        verify(tourLogMapper).mapToDto(anyList());
    }


}
