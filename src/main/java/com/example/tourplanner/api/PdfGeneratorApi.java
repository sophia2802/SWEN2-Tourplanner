package com.example.tourplanner.api;

import com.example.tourplanner.service.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("reports")

public class PdfGeneratorApi {

    @Autowired
    private PdfGenerator pdfGeneratorService;

    @GetMapping("/tour/{id}")
    public ResponseEntity<byte[]> getTourReport(@PathVariable Long id) {
        try {
            String outputPath = "tour_report_" + id + ".pdf";
            pdfGeneratorService.generateTourReport(id, outputPath);

            byte[] pdfBytes = Files.readAllBytes(Paths.get(outputPath));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + outputPath);
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}