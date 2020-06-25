package com.praktikum.spapp.Service;

import android.accounts.AuthenticatorException;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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

    public ArrayList<Project> fetchAllProjects() throws Exception {


        Request request = HttpClient.httpRequestMaker("/api/project", "get");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        String successString = Utils.parseForJsonObject(responseString,"result");

        if (isRefreshed) {
            return fetchAllProjects();
        } else {
            return new Gson().fromJson(successString, new TypeToken<ArrayList<Project>>() {
            }.getType());
        }

    }

    //TODO GIGA TODO MOTHERFUCKER KYS SPAß NEIN TUS NICHT ;)))))) DOCH TUS JKgit
    public ArrayList<Project> fetchProjectsOnlyFromUser() throws IOException {
        Request request = new Request.Builder()
                .url(api + "/api/project/")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0X21vZCIsImlhdCI6MTU5MTYxMzI0MywiZXhwIjoxNTkxNjk5NjQzfQ.vMTN-TftGV1A4gJGh9NDKxRtMS3ndpyMrJcjhjsNjHHvmnYFWx3fEwDfBF_qeZqv2N3XPo4XB-XtBQwSSOf69Q")
                .build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        String successString = Utils.parseForJsonObject(responseString,"result");


        Gson gson = new Gson();
        //
        return gson.fromJson(successString, new TypeToken<ArrayList<Project>>() {
        }.getType());

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
