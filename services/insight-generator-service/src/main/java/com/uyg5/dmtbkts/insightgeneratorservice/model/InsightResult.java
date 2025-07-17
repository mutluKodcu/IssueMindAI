package com.uyg5.dmtbkts.insightgeneratorservice.model;

public class InsightResult {
    private String module;
    private double score;
    private String suggestion;

    // Getters and setters
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getSuggestion() { return suggestion; }
    public void setSuggestion(String suggestion) { this.suggestion = suggestion; }
}