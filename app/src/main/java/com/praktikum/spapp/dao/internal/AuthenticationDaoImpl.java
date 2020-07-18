package com.praktikum.spapp.dao.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.AuthenticationDao;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationDaoImpl extends AbstractDao implements AuthenticationDao {

    public Session logon(String nameOrEmail, String password) {
        Session session = null;

        // create jsonString GSON by map
        Map<String, String> map = new HashMap<>();
        if (Utils.isEmail(nameOrEmail)) {
            map.put("email", nameOrEmail);
        } else {
            map.put("username", nameOrEmail);
        }
        map.put("password", password);
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(map);

        // create request body
        RequestBody requestBody = RequestBody.create(jsonString, JSON);
        // make request
        Request request = new Request.Builder()
                .url(api + "/api/auth/signin")
                .post(requestBody)
                .build();

        try {
            Response response = new OkHttpClient().newCall(request).execute();
            // have to create variable, because stream is closed or so
            String responseString = response.body().string();
            if (Utils.isSuccess(responseString)) {
                session = new Gson().fromJson(responseString, Session.class);
                session.setCurrentUsername(nameOrEmail);
                return session;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

    public User authenticateUser(String nameOrEmail, Session session) throws IOException {

        Request request = new Request.Builder()
                .url(api + "/api/user/getUserByUserName/" + nameOrEmail)
                .header("Authorization", session.getTokenType() + " " + session.getAccessToken())
                .get()
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        return new Gson().fromJson(response.body().string(), new TypeToken<User>() {
        }.getType());


    }
}
