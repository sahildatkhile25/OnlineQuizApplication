package com.quiz.model;

public class Quiz {

    private int id;
    private String title;
    private String topic;

    public Quiz() {
    }

    public Quiz(int id, String title, String topic) {
        this.id = id;
        this.title = title;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}