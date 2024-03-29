package com.example.tourplanner.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TourLogDto {

    private Long id;
    private Long tourId; //refers to the associated tour
    private LocalDate date;
    private String comment;
    private Integer difficulty;
    private Double totalDistance;
    private String totalTime;
    private Integer rating;
}
