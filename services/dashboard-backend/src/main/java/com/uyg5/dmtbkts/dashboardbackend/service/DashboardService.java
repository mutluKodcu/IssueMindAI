package com.uyg5.dmtbkts.dashboardbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final RedisTemplate<String, String> redisTemplate;

    public Map<String, String> getAllFeedbacks() {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.entries("feedbacks");
    }
}
