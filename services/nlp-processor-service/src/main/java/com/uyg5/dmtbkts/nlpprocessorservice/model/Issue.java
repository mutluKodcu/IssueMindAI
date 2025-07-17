package com.uyg5.dmtbkts.nlpprocessorservice.model;

public class Issue {
    private String id;
    private String title;
    private String description;
    private String module;
    private String type;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}