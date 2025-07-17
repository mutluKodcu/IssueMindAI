package com.uyg5.dmtbkts.feedbackanalyzerservice.model;

import java.io.Serializable;

public class FeedbackAnalysis implements Serializable {
    private String issueId;
    private String comment;
    private int rating;
    private long timestamp;

    public FeedbackAnalysis() {
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
