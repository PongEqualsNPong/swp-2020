package com.praktikum.spapp.service;

import com.google.gson.JsonObject;
import com.praktikum.spapp.models.Project;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public interface ProjectService {

    Long createProject(Project project) throws Exception;

    Long createProjectFull(Project project) throws Exception;

    ArrayList<Project> fetchAllProjects() throws Exception;

    ArrayList<Project> fetchCurrentUserProjects() throws IOException;

    void updateProject(Long id, JsonObject data) throws IOException;

    void deleteProject(Long id) throws Exception;


}
