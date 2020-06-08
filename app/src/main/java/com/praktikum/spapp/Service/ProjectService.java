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
                .url(api + "/api/project/init")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTU3MzIzNCwiZXhwIjoxNTkxNjU5NjM0fQ.b4hxTAeww8biGC-MjHbqBiCTSN-UL_5cWxSiWWrxRoqqFMAlsARTgbcbHD8iyAjblvJv5QwUnkSMKHJvdFHZ1A")
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // create the response string
            String responseString =  response.body().string();
            System.out.println(responseString);
            return responseString;
        }
    }

    public ArrayList<Project> fetchAllProjects() throws IOException {
        Request request = new Request.Builder()
                .url(api + "/project")
                .header("Access-Control-Allow-Origin", "*")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTYwNzY5MSwiZXhwIjoxNTkxNjk0MDkxfQ.eL-F5RuYkPIGU1Xi_iDe7AS7V1yaB7rOnWrYagKfKogRRf9661R7s9sxFh0xAD2Rl5mAMxPLj14j-EbvDmoR6A")
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
//            if(iterateProjectArrayList.next()!= Token.getUserId()){
//                iterateProjectArrayList.remove();
//            }
//        }
//        return projectArrayList;
//    }
}
