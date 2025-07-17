package com.uyg5.dmtbkts.feedbackcollectorservice.controller;

import com.uyg5.dmtbkts.feedbackcollectorservice.model.Feedback;
import com.uyg5.dmtbkts.feedbackcollectorservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public void submitFeedback(@RequestBody Feedback feedback) {
        feedbackService.sendFeedback(feedback);
    }
}