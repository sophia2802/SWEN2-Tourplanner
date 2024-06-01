package com.example.tourplanner.service;

import java.io.IOException;

public interface TourImportExportService {
    void exportTours(String filePath) throws IOException;
    void importTours(String filePath) throws IOException;
}