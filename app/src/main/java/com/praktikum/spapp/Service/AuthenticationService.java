package com.praktikum.spapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.models.Token;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService extends Service {

    private static Token token;

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        AuthenticationService.token = token;
    }

    public static void loginOnServer(String nameOrEmail, String password) throws IOException {

        // create jsonString GSON by map
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", nameOrEmail);
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

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        Type listType = new TypeToken<Token>() {
        }.getType();
        setToken(gson.fromJson(responseString, listType));
    }
}
