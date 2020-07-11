package com.praktikum.spapp.dao;

import com.google.gson.JsonObject;
import com.praktikum.spapp.models.Project;

import java.util.ArrayList;

public interface ProjectDao {

    Long createProject(Project project);

    Long createProjectFull(Project project);

    ArrayList<Project> fetchAllProjects();

    ArrayList<Project> fetchCurrentUserProjects();

    void updateProject(Long id, JsonObject data);

    void deleteProject(Long id);
}
