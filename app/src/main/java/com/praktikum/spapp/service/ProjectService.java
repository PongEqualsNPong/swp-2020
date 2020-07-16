package com.praktikum.spapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditProjectForm;
import com.praktikum.spapp.models.Project;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectService extends Service {

    public ProjectService() {
        super();
    }

    public String projectCreate(Project project) throws Exception {

        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(project));

        Request request = HttpClient.httpRequestMaker("/api/project/init", "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return projectCreate(project);
        } else {
            return responseString;
        }
    }

    public String projectCreateFull(Project project) throws Exception {
        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(project));

        Request request = HttpClient.httpRequestMaker("/api/project/initFull", "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return projectCreate(project);
        } else {
            return responseString;
        }
    }

    public String projectDelete(Project project) throws Exception {

        Request request = HttpClient.httpRequestMaker("/api/project/delete/" + project.getId(), "delete");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return projectDelete(project);
        } else {
            return responseString;
        }

    }

    public ArrayList<Project> fetchAllProjects() throws Exception {

        Request request = HttpClient.httpRequestMaker("/api/project", "get");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        String successString = Utils.parseForJsonObject(responseString, "result");

        if (isRefreshed) {
            return fetchAllProjects();
        } else {
            return new Gson().fromJson(successString, new TypeToken<ArrayList<Project>>() {
            }.getType());
        }

    }
    //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5NDU4ODU1NywiZXhwIjoxNTk0Njc0OTU3fQ.ZsXnl5fXuE5lL2hx3IpE20cL0qz2DOYwlrfO0FiCaBIzwN_-bonBzO2PH6bPyLWuCzX0kGg44e6vF2WFZVtCPQ
    //TODO GIGA TODO MOTHERFUCKER KYS SPAß NEIN TUS NICHT ;)))))) DOCH TUS JKgit
    public ArrayList<Project> fetchProjectsOnlyFromUser() throws IOException {
        Request request = new Request.Builder()
                .url(api + "/api/project/")
                .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        String successString = Utils.parseForJsonObject(responseString, "result");


        Gson gson = new Gson();
        //
        return gson.fromJson(successString, new TypeToken<ArrayList<Project>>() {
        }.getType());

    }

    public String updateProject(EditProjectForm editProjectFormForm, int projectNr) throws Exception {
        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(editProjectFormForm));
        Request request = HttpClient.httpRequestMaker("/api/project/update/" + projectNr, "post", data);
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString;
    }

    public String deleteProject(EditProjectForm editProjectFormForm, int projectNr) throws Exception {
        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(editProjectFormForm));
        Request request = HttpClient.httpRequestMaker("/api/project/delete/" + projectNr, "delete", data);
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString;
    }
//        Gson gson = new GsonBuilder().create();
//
//        ArrayList<Project> projectArrayList = fetchAllProjects();
//
//        Iterator<Project> iterateProjectArrayList = projectArrayList.iterator();
//        while(iterateProjectArrayList.hasNext()) {
//            Project toCompare = iterateProjectArrayList.next();
//            for(User handler : toCompare.getHandler())
//
//            if(iterateProjectArrayList.next()!= Token.getUserId()){
//                iterateProjectArrayList.remove();
//            }
//        }
//        return projectArrayList;
//    }

    public String editProject(JSONObject data, int id) throws IOException {
        Request request = HttpClient.httpRequestMaker("/api/project/update/" + id, "post", data);
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString;

    }
}
