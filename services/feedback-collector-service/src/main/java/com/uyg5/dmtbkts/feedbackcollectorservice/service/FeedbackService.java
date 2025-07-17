package com.uyg5.dmtbkts.feedbackcollectorservice.service;

import com.uyg5.dmtbkts.feedbackcollectorservice.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private static final String TOPIC = "feedback-events";

    @Autowired
    private KafkaTemplate<String, Feedback> kafkaTemplate;

    public void sendFeedback(Feedback feedback) {
        kafkaTemplate.send(TOPIC, feedback);
    }
}