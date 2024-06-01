package com.example.tourplanner.service.impl;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.service.TourImportExportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TourImportExportServiceImpl implements TourImportExportService {

    @Autowired
    private TourRepository tourRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void exportTours(String filePath) throws IOException {
        List<TourEntity> tours = tourRepository.findAll();
        objectMapper.writeValue(new File(filePath), tours);
    }

    @Override
    public void importTours(String filePath) throws IOException {
        TourEntity[] tours = objectMapper.readValue(new File(filePath), TourEntity[].class);
        for (TourEntity tour : tours) {
            tourRepository.save(tour);
        }
    }
}
