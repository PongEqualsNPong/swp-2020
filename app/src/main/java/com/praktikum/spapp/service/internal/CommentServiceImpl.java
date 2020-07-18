package com.praktikum.spapp.service.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.CommentDao;
import com.praktikum.spapp.dao.internal.CommentDaoImpl;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Comment;

import com.praktikum.spapp.service.CommentService;
import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;


public class CommentServiceImpl implements CommentService {
    CommentDao dao = new CommentDaoImpl(SessionManager.getSession());

    @Override
    public Comment createComment(Long projectId, boolean restricted, String message) throws ResponseException {
        return null;
    }

    @Override
    public ArrayList<Comment> getAllComments(Long projectId) throws ResponseException {
        return null;
    }

    @Override
    public ArrayList<Comment> getPublicComments(Long projectId) throws ResponseException {
        return null;
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
}
