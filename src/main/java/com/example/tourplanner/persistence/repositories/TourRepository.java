package com.example.tourplanner.persistence.repositories;

import com.example.tourplanner.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {
    List<TourEntity> findByNameContainingIgnoreCase(String keyword);

}
