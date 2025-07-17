package com.uyg5.dmtbkts.nlpprocessorservice.service;

import com.uyg5.dmtbkts.nlpprocessorservice.model.Issue;
import com.uyg5.dmtbkts.nlpprocessorservice.model.AnalysisResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NLPService {

    private final KafkaTemplate<String, AnalysisResult> kafkaTemplate;

    public NLPService(KafkaTemplate<String, AnalysisResult> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processIssue(Issue issue) {
        String description = issue.getDescription().toLowerCase();
        String type = "unknown";

        if (description.contains("bug") || description.contains("hata")) {
            type = "bug";
        } else if (description.contains("ekle") || description.contains("sil") || description.contains("güncelle")) {
            type = "crud";
        } else if (description.contains("refactor")) {
            type = "refactor";
        }

        AnalysisResult result = new AnalysisResult();
        result.setIssueId(issue.getId());
        result.setModule(issue.getModule());
        result.setDetectedType(type);
        result.setComment("NLP ile tespit edilen tür: " + type);

        kafkaTemplate.send("analysis-results", result);
    }
}