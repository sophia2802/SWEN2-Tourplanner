package com.example.tourplanner.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tour_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TourLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //one tour has many tourLogs,
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tour;

    @Column(nullable = false)
    private LocalDate date;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private Integer difficulty;

    @Column(name = "total_distance", nullable = false)
    private Double totalDistance;

    @Column(name = "total_time", nullable = false)
    private String totalTime;

    @Column(nullable = false)
    private Integer rating;
}
