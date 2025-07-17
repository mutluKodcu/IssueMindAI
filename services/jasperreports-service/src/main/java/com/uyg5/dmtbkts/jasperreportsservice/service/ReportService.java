package com.uyg5.dmtbkts.jasperreportsservice.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    public ResponseEntity<byte[]> generateReport(String issueId) {
        try {
            InputStream reportStream = new ClassPathResource("reports/sample.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("issueId", issueId);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
