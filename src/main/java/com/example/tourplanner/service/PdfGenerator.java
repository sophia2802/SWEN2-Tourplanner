package com.example.tourplanner.service;

import com.example.tourplanner.persistence.entities.TourEntity;
import com.example.tourplanner.persistence.entities.TourLogEntity;
import com.example.tourplanner.persistence.repositories.TourRepository;
import com.example.tourplanner.persistence.repositories.TourLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class PdfGenerator {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourLogRepository tourLogRepository;

    private TemplateEngine templateEngine;

    public PdfGenerator() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public String parseThymeleafTemplate(Long tourId) {
        TourEntity tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour not found"));
        List<TourLogEntity> tourLogs = tourLogRepository.findByTourId(tourId);

        Context context = new Context();
        context.setVariable("tour", tour);
        context.setVariable("tourLogs", tourLogs);

        return templateEngine.process("thymeleaf/tour_report", context);
    }

    void generatePdfFromHtml(String html, String outputPath) throws Exception {
        OutputStream outputStream = new FileOutputStream(outputPath);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    public void generateTourReport(Long tourId, String outputPath) throws Exception {
        String html = parseThymeleafTemplate(tourId);
        generatePdfFromHtml(html, outputPath);
    }
}