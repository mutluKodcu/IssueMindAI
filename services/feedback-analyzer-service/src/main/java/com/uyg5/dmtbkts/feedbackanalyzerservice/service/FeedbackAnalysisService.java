package com.uyg5.dmtbkts.feedbackanalyzerservice.service;

import com.uyg5.dmtbkts.feedbackanalyzerservice.model.FeedbackAnalysis;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeedbackAnalysisService {

    private static final String KEY = "Feedback";

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, FeedbackAnalysis> hashOperations;

    public FeedbackAnalysisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void saveFeedback(FeedbackAnalysis feedback) {
        hashOperations.put(KEY, feedback.getIssueId(), feedback);
    }
}
