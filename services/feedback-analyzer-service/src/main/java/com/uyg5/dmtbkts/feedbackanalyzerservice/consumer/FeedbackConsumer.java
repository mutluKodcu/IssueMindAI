package com.uyg5.dmtbkts.feedbackanalyzerservice.consumer;

import com.uyg5.dmtbkts.feedbackanalyzerservice.model.FeedbackAnalysis;
import com.uyg5.dmtbkts.feedbackanalyzerservice.service.FeedbackService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConsumer {

    private final FeedbackService feedbackService;

    public FeedbackConsumer(FeedbackAnalysisService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @KafkaListener(topics = "feedback-events", groupId = "feedback-analyzer-group")
    public void consume(FeedbackAnalysis feedback) {
        feedbackService.saveFeedback(feedback);
    }
}
