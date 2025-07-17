package com.uyg5.dmtbkts.nlpprocessorservice.model;

public class AnalysisResult {
    private String issueId;
    private String module;
    private String detectedType;
    private String comment;

    public String getIssueId() { return issueId; }
    public void setIssueId(String issueId) { this.issueId = issueId; }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public String getDetectedType() { return detectedType; }
    public void setDetectedType(String detectedType) { this.detectedType = detectedType; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}