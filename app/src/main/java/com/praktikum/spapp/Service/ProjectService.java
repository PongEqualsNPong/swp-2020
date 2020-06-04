package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

    public String fetchAllProjects() throws IOException {
        Request request = new Request.Builder()
                .url(api)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public ArrayList<Project> fetchProjectsOnlyFromUser(User user) throws IOException {
        Gson gson = new GsonBuilder().create();

        String responseString = fetchAllProjects();
        ArrayList<Project> projectArrayList = gson.fromJson(responseString, new TypeToken<ArrayList<Project>>() {}.getType());

        Iterator<Project> iterateUserArrayList = projectArrayList.iterator();
        while(iterateUserArrayList.hasNext()) {
            if(iterateUserArrayList.next().getName()!= Token.getUserId()){
                iterateUserArrayList.remove();
            }
        }
        return projectArrayList;
    }
}
