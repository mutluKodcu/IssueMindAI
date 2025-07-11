package com.uyg5.dmtbkts.issueproducerservice.kafka;

import com.uyg5.dmtbkts.issueproducerservice.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IssueProducer {

    private static final String TOPIC = "issue-events";

    @Autowired
    private KafkaTemplate<String, Issue> kafkaTemplate;

    public void sendIssue(Issue issue) {
        kafkaTemplate.send(TOPIC, issue);
    }
}
