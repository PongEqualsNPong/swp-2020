package com.praktikum.spapp.service.internal;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.CommentDao;
import com.praktikum.spapp.dao.internal.CommentDaoImpl;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Comment;

import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.service.CommentService;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CommentServiceImpl extends Service implements CommentService {
    CommentDao dao;

    public CommentServiceImpl(Session session) {
        dao = new CommentDaoImpl(SessionManager.getSession());
        this.session = session;
    }

    @Override
    public Comment createComment(Long projectId, boolean restricted, String message) throws ResponseException {
        return dao.createComment(projectId, restricted, message);
    }

    @Override
    public ArrayList<Comment> getAllComments(Long projectId) throws ResponseException {
        return dao.getComments(projectId);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<Comment> getPublicComments(Long projectId) throws ResponseException {
        ArrayList<Comment> allComments = dao.getComments(projectId);
        List<Comment> filtered = allComments.stream().filter(c -> !c.isRestricted()).collect(Collectors.toList());
        return new ArrayList<>(filtered);
    }

    @Override
    public ArrayList<Comment> getFilteredComments(Long projectId) throws ResponseException {
        return null;
    }

    @Override
    public Comment updateComment(Long commentId, boolean restricted, String message) throws ResponseException {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) throws ResponseException {

    }


    /**
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

     Request request = HttpClient.httpRequestMaker("/api/comments/" + commentId, "post", data);
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
     */


}
