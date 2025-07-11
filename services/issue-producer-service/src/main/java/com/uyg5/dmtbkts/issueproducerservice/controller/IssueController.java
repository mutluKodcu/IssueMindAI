package com.uyg5.dmtbkts.issueproducerservice.controller;

import com.uyg5.dmtbkts.issueproducerservice.kafka.IssueProducer;
import com.uyg5.dmtbkts.issueproducerservice.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueProducer issueProducer;

    @PostMapping
    public String postIssue(@RequestBody Issue issue) {
        issueProducer.sendIssue(issue);
        return "Issue sent to Kafka!";
    }
}
