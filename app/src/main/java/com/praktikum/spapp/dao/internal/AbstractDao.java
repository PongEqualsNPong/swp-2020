package com.praktikum.spapp.dao.internal;

import com.google.gson.JsonObject;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.service.internal.AuthenticationServiceImpl;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;


/**
 * The abstract dao implementation class.
 */
public abstract class AbstractDao {
    protected Session session;
    protected static final OkHttpClient client = new OkHttpClient();
    protected static final String api = "http://192.168.178.176:8081";
    // need this for okhttp
    protected static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    public Response httpRequestMaker(String path, String method, JsonObject json) throws IOException {

        RequestBody body = RequestBody.create(json.toString(), JSON);

        switch (method) {
            case "put":
                Request request = new Request.Builder()
                        .url(api + path)
                        .header("Authorization", session.getTokenType() + " " + session.getAccessToken())
                        .put(body)
                        .build();
                return client.newCall(request).execute();

            case "post":
                request = new Request.Builder()
                        .url(api + path)
                        .header("Authorization", session.getTokenType() + " " + session.getAccessToken())
                        .post(body)
                        .build();
                return client.newCall(request).execute();

            case "get":
                request = new Request.Builder()
                        .url(api + path)
                        .header("Authorization", session.getTokenType() + " " + session.getAccessToken())
                        .get()
                        .build();
                return client.newCall(request).execute();

            case "delete":
                request = new Request.Builder()
                        .url(api + path)
                        .header("Authorization", session.getTokenType() + " " + session.getAccessToken())
                        .delete()
                        .build();
                return client.newCall(request).execute();
        }
        return null;
    }

    public Response httpRequestMaker(String path, String method) throws IOException {
        return httpRequestMaker(path, method, new JsonObject());
    }

    public static void responseCheck(String responseString) throws ResponseException {
        if (!Utils.isSuccess(responseString)) {
            throw new ResponseException(Utils.parseForJsonObject(responseString, "Error"));
        }
    }
}
