package com.example.tourplanner.service.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TourDto {
    private Long id;
    private String name;
    private String description;
    private String arrival;
    private String departure;
    private String transportType;
    private Double distance;
    private String estimatedTime;
    private String routeImage;
}
