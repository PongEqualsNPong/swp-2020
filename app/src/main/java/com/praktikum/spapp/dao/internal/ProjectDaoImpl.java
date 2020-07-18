package com.praktikum.spapp.dao.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.ProjectDao;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.Session;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectDaoImpl extends AbstractDao implements ProjectDao {

    public ProjectDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public Long createProject(Project project) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(project));
        try {
            Response response = httpRequestMaker("/api/project/init", "post", data);
            String responseString = response.body().string();
            responseCheck(responseString);
            String result = Utils.parseForJsonObject(responseString, "projectId");
            return Long.parseLong(result);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public Long createProjectFull(Project project) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(project));
        try {
            Response response = httpRequestMaker("/api/project/initFull", "post", data);
            String responseString = response.body().string();
            responseCheck(responseString);
            return Long.parseLong(Utils.parseForJsonObject(responseString, "projectId"));
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public ArrayList<Project> fetchAllProjects() throws ResponseException {
        try {
            Response response = this.httpRequestMaker("/api/project", "get");
            String responseString = response.body().string();
            responseCheck(responseString);
            String resultString = Utils.parseForJsonObject(responseString, "result");
            return new Gson().fromJson(resultString, new TypeToken<ArrayList<Project>>() {
            }.getType());

        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public ArrayList<Project> fetchCurrentUserProjects() throws ResponseException {
        ArrayList<Project> list = null;
        try {
            Response response = this.httpRequestMaker("/api/project/", "get");
            String responseString = response.body().string();
            responseCheck(responseString);
            String resultString = Utils.parseForJsonObject(responseString, "result");
            return new Gson().fromJson(resultString, new TypeToken<ArrayList<Project>>() {
            }.getType());

        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void updateProject(Long id, JsonObject data) throws ResponseException {
        try {
            Response response = this.httpRequestMaker("/api/project/" + id.toString(), "put", data);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void deleteProject(Long id) throws ResponseException {
        try {
            Response response = this.httpRequestMaker("/api/project/delete/" + id.toString(), "delete");
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (Exception e) {
            throw new ResponseException(e);
        }
    }
}
