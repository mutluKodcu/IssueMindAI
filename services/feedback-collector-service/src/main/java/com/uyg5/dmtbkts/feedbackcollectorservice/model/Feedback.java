package com.uyg5.dmtbkts.feedbackcollectorservice.model;

public class Feedback {
    private String issueId;
    private String comment;
    private int rating;
    private long timestamp;

    // Getters and Setters
    public String getIssueId() { return issueId; }
    public void setIssueId(String issueId) { this.issueId = issueId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}