package com.uyg5.dmtbkts.insightgeneratorservice.consumer;

import com.uyg5.dmtbkts.insightgeneratorservice.model.InsightResult;
import com.uyg5.dmtbkts.insightgeneratorservice.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InsightConsumer {

    @Autowired
    private InsightService insightService;

    @Autowired
    private KafkaTemplate<String, InsightResult> kafkaTemplate;

    @KafkaListener(topics = "analysis-results", groupId = "insight-group")
    public void consume(String message) {
        // Dummy parsing (örneğin {"module":"A", "analysis":"refactor needed"})
        String module = message.contains("module") ? "A" : "Unknown";
        String analysis = message;

        InsightResult result = insightService.analyze(module, analysis);
        kafkaTemplate.send("insight-results", result);
    }
}