package com.uyg5.dmtbkts.dashboardbackend.controller;

import com.uyg5.dmtbkts.dashboardbackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/feedbacks")
    public Map<String, String> getAllFeedbacks() {
        return dashboardService.getAllFeedbacks();
    }
}
