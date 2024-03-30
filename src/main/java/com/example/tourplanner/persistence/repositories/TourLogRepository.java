package com.example.tourplanner.persistence.repositories;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {
    List<TourLogEntity> findByTourId(Long tourId);  //finds every tourlog for a specific tour
}
