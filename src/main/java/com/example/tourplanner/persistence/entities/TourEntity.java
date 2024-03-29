package com.example.tourplanner.persistence.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @Column(nullable = false)
    private String transportType;

    @Column(nullable = false)
    private Double distance;

    @Column(name = "estimated_time")
    private String estimatedTime;

    @Column(name = "route_image")
    private String routeImage;
}
