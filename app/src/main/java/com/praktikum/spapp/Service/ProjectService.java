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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

        return gson.fromJson(responseString, new TypeToken<ArrayList<Project>>() {}.getType());
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
//            if(iterateUserArrayList.next()!= Token.getUserId()){
//                iterateUserArrayList.remove();
//            }
//        }
//        return projectArrayList;
//    }
}
