package com.example.tourplanner.api;

import com.example.tourplanner.service.TourImportExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "tour")

public class TourImportExportApi {

    @Autowired
    private TourImportExportService tourImportExportService;

    @GetMapping("/export")
    public void exportTours() {
        try {
            tourImportExportService.exportTours("exportTours.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/import")
    public void importTours() {
        try {
            tourImportExportService.importTours("importTours.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
