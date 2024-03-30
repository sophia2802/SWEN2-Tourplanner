package com.example.tourplanner.persistence.repositories;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {
}
