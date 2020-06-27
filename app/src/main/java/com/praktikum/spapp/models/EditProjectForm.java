package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.ProjectStatus;
import com.praktikum.spapp.models.enums.ProjectType;

import java.util.ArrayList;

public class EditProjectForm {

    int id;
    String name;
    String description;
    ArrayList<Comment> comments;
    ArrayList<Appointment> appointments;
    ArrayList<User> handler;
    ArrayList<User> processor;
    ProjectType type;
    ProjectStatus projectStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<User> getHandler() {
        return handler;
    }

    public void setHandler(ArrayList<User> handler) {
        this.handler = handler;
    }

    public ArrayList<User> getProcessor() {
        return processor;
    }

    public void setProcessor(ArrayList<User> processor) {
        this.processor = processor;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }


}
