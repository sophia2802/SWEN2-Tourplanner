package com.example.tourplanner.service.mapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.persistence.repositories.TourLogRepository;
import com.example.tourplanner.service.PdfGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Optional;

class PdfGeneratorTest {

    @Mock
    private TourRepository tourRepository;

    @Mock
    private TourLogRepository tourLogRepository;

    @InjectMocks
    private PdfGenerator pdfGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseThymeleafTemplate() {
        Long tourId = 1L;
        TourEntity tour = new TourEntity();
        TourLogEntity log1 = new TourLogEntity();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        when(tourLogRepository.findByTourId(tourId)).thenReturn(Arrays.asList(log1));

        String result = pdfGenerator.parseThymeleafTemplate(tourId);

        assertThat(result).isNotEmpty();
        verify(tourRepository).findById(tourId);
        verify(tourLogRepository).findByTourId(tourId);
    }

    @Test
    void testParseThymeleafTemplateWithNonExistentTour() {
        Long tourId = 99L; //Assume 99 is an invalid ID
        when(tourRepository.findById(tourId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pdfGenerator.parseThymeleafTemplate(tourId);
        });

        assertThat(exception.getMessage()).isEqualTo("Tour not found");
        verify(tourRepository).findById(tourId);
        verify(tourLogRepository, never()).findByTourId(any());
    }
}