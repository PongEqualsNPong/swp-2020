package com.praktikum.spapp.dao.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.ProjectDao;
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
    public Long createProject(Project project) {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(project));
        try {
            Response response = httpRequestMaker("/api/project/init", "post", data);
            return Long.parseLong(Utils.parseForJsonObject(response.body().string(), "projectId"));
        } catch (IOException e) {
            return 0L;
        }
    }

    @Override
    public Long createProjectFull(Project project) {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(project));
        try {
            Response response = httpRequestMaker("/api/project/initFull", "post", data);
            return Long.parseLong(Utils.parseForJsonObject(response.body().string(), "projectId"));
        } catch (IOException e) {
            return 0L;
        }
    }

    @Override
    public ArrayList<Project> fetchAllProjects() {
        ArrayList<Project> list = null;
        try {
            Response response = this.httpRequestMaker("/api/project", "get");
            String responseString = Utils.parseForJsonObject(response.body().toString(), "result");
            return new Gson().fromJson(responseString, new TypeToken<ArrayList<Project>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Project> fetchCurrentUserProjects() {
        ArrayList<Project> list = null;
        try {
            Response response = this.httpRequestMaker("/api/project/", "get");
            String responseString = Utils.parseForJsonObject(response.body().toString(), "result");
            return new Gson().fromJson(responseString, new TypeToken<ArrayList<Project>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateProject(Long id, JsonObject data) {
        try {
            Response response = this.httpRequestMaker("/api/project/" + id.toString(), "put", data);
            if (Utils.isSuccess(response.body().string()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(Long id) {
        try {
            Response response = this.httpRequestMaker("/api/project/" + id.toString(), "delete");
            if (Utils.isSuccess(response.body().string())) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
