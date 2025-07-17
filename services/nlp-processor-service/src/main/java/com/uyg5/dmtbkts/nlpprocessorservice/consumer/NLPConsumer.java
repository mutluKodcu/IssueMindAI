package com.uyg5.dmtbkts.nlpprocessorservice.consumer;

import com.uyg5.dmtbkts.nlpprocessorservice.model.Issue;
import com.uyg5.dmtbkts.nlpprocessorservice.service.NLPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NLPConsumer {

    @Autowired
    private NLPService nlpService;

    @KafkaListener(topics = "issue-events", groupId = "nlp-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Issue issue) {
        nlpService.processIssue(issue);
    }
}