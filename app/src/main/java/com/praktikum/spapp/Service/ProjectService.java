package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.*;
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

//    public String projectCreate(Project project) throws JSONException, IOException {
//
//        String data = new JSONObject()
//                .put("name", project.getName())
//                .put("description", project.getDescription())
//                .toString();
//
//        RequestBody requestBody = RequestBody.create(data, JSON);
//        Request request = new Request.Builder()
//                .url(api + "/api/project/init")
//                .header("Authorization", "Bearer " + "..--")
//                .post(requestBody)
//                .build();
//
//        methode(path, string jsonobject)
//        try (Response response = client.newCall(request).execute()) {
//            // create the response string
//            String responseString = response.body().string();
//            System.out.println(responseString);
//            return responseString;
//        }
//    }


    public ArrayList<Project> fetchAllProjects() throws IOException {


        Request request = new Request.Builder()
                .url(api + "/api/project")
                .header("Authorization", "Bearer " + "")

                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println(responseString);


            Gson gson = new Gson();
            //
            Type listType = new TypeToken<ArrayList<Project>>() {
            }.getType();
            ArrayList<Project> projectArrayList = gson.fromJson(successString, listType);

            return projectArrayList;
        }
        return null;
    }
//TODO GIGA TODO MOTHERFUCKER KYS SPAß NEIN TUS NICHT ;)))))) DOCH TUS JKgit
    public ArrayList<Project> fetchProjectsOnlyFromUser() throws IOException {
        Request request = new Request.Builder()
                .url(api + "/api/project/")
                .header("Access-Control-Allow-Origin", "*")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0X21vZCIsImlhdCI6MTU5MTYxMzI0MywiZXhwIjoxNTkxNjk5NjQzfQ.vMTN-TftGV1A4gJGh9NDKxRtMS3ndpyMrJcjhjsNjHHvmnYFWx3fEwDfBF_qeZqv2N3XPo4XB-XtBQwSSOf69Q")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println(responseString);

        if (Utils.isSuccess(responseString)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseString);
            JsonObject resultAsJsonObject = element.getAsJsonObject();
            JsonElement isSuccess = resultAsJsonObject.get("result");
            String successString = isSuccess.toString();
            System.out.print(successString);
            Gson gson = new Gson();
            //
            Type listType = new TypeToken<ArrayList<Project>>() {
            }.getType();
            ArrayList<Project> projectArrayList = gson.fromJson(successString, listType);

            return projectArrayList;
        }
        return null;
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
}
