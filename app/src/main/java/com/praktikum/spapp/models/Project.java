package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.ProjectType;

import java.io.Serializable;

public class Project implements Serializable {

    String id;
    String name;
    String description;
    ProjectType type;

    public Project(String id, String name, String description, ProjectType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Project(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Project() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }
}


