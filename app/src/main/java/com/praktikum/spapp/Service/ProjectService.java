package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class ProjectService extends Service {

    public ProjectService() {
        super();
    }

    public String projectCreate(Project project) throws JSONException, IOException {

        String data = new JSONObject()
                .put("name", project.getName())
                .put("description", project.getDescription())
                .toString();

        RequestBody requestBody = RequestBody.create(data, JSON);
        Request request = new Request.Builder()
                .url(api)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // create the response string
            return response.body().string();
        }
    }

    public ArrayList<Project> fetchAllProjects() throws IOException {
        Request request = new Request.Builder()
                .url(api)
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(responseString, new TypeToken<ArrayList<Project>>() {
        }.getType());
    }

    String authCode = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTYxNzc5MSwiZXhwIjoxNTkxNzA0MTkxfQ.8d-wDngdAlpAbd-mz7Q0z4UyK8RhiYC0HxoOGyjaSFCAArfCItxNTSVoN-DNwUxj_vQMzERZdUhdw_sT-rhAmA";
    public ArrayList<Project> fetchProjectsDetail() throws IOException {
        new Thread(() -> {
        }).start();
        System.out.print(UserService.accessToken);
        Request request = new Request.Builder()
                .url(api + "/api/project")
                .header("Authorization", "Bearer " + authCode)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        Gson gson = new GsonBuilder().create();

        return gson.fromJson(responseString, new TypeToken<ArrayList<Project>>() {
        }.getType());
/*
        if (Utils.isSuccess(responseString)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseString);
            JsonObject resultAsJsonObject = element.getAsJsonObject();
            JsonElement isSuccess = resultAsJsonObject.get("result");
            String successString = isSuccess.toString();
            System.out.print(successString);
            Gson gson = new Gson();
            //
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            ArrayList<Project> projectArrayList = gson.fromJson(successString, listType);
            return projectArrayList;
        }
        return null;
        */
    }

//    public ArrayList<Project> fetchProjectsOnlyFromUser(User user) throws IOException {
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
}
