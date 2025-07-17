package com.uyg5.dmtbkts.jasperreportsservice.controller;

import com.uyg5.dmtbkts.jasperreportsservice.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<byte[]> generateReport(@PathVariable String issueId) {
        return reportService.generateReport(issueId);
    }
}
