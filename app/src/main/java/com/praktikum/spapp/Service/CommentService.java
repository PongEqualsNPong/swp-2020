package com.praktikum.spapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Comment;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Request;
import okhttp3.Response;

import static com.praktikum.spapp.Service.Service.client;

public class CommentService {

    public CommentService() {
        super();
    }

    public String commentFetch(int projectId) throws Exception {

        Request request = HttpClient.httpRequestMaker( "/api/projects/" + projectId + "/comments", "get");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return commentFetch(projectId);
        } else {
            return responseString;
        }
    }

    public String commentPost(int projectId, Comment comment) throws Exception {

        Gson gson = new GsonBuilder().create();
        //PAYLOAD
        JSONObject data = new JSONObject(gson.toJson(comment));

        Request request = HttpClient.httpRequestMaker( "/api/projects/" + projectId + "/comments", "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return commentPost(projectId, comment);
        } else {
            return responseString;
        }
    }

    public String commentEdit(int commentId, Comment comment) throws Exception {

        Gson gson = new GsonBuilder().create();
        //PAYLOAD
        JSONObject data = new JSONObject(gson.toJson(comment));

        Request request = HttpClient.httpRequestMaker( "/api/comments/" + commentId, "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return commentEdit(commentId, comment);
        } else {
            return responseString;
        }
    }

    public String commentDelete(int commentId) throws Exception {
        Request request = HttpClient.httpRequestMaker("/api/comments/" + commentId, "delete");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return commentDelete(commentId);
        } else {
            return responseString;
        }
    }


}
