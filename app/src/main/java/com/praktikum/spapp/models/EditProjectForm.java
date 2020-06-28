package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.ProjectStatus;
import com.praktikum.spapp.models.enums.ProjectType;

import java.util.ArrayList;

public class EditProjectForm {

    private String name;
    private String description;
    private ArrayList<String> handler;
    private ArrayList<String> processor;
    private ProjectType projectType;
    private ProjectStatus projectStatus;

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

    public ArrayList<String> getHandler() {
        return handler;
    }

    public void setHandler(ArrayList<String> handler) {
        this.handler = handler;
    }

    public ArrayList<String> getProcessor() {
        return processor;
    }

    public void setProcessor(ArrayList<String> processor) {
        this.processor = processor;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
}
