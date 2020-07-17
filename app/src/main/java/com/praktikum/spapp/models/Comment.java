package com.praktikum.spapp.models;

import java.io.Serializable;

public class Comment implements Serializable {

    private int id;
    private String content;
    private boolean restricted;
    private boolean wasEdited;
    private String creationTime;
    private User author;

    public Comment(int id, String content, boolean restricted, boolean edited, String creationTime, User author) {

        this.id = id;
        this.content = content;
        this.restricted = restricted;
        this.wasEdited = edited;
        this.creationTime = creationTime;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public boolean isWasEdited() {
        return wasEdited;
    }

    public void setWasEdited(boolean wasEdited) {
        this.wasEdited = wasEdited;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
