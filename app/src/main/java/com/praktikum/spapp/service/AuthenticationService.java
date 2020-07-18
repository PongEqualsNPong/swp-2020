package com.praktikum.spapp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.util.HashMap;
import java.util.Map;

import static com.praktikum.spapp.common.HttpClient.httpRequestMaker;

public class AuthenticationService extends Service {

    private static Token token;


    public static String loginOnServer(String nameOrEmail, String password) throws Exception {

        // create jsonString GSON by map
        Map<String, String> map = new HashMap<>();
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

        Response response = new OkHttpClient().newCall(request).execute();
        String responseString = response.body().string();

        //if login successful then set token with the currentUser
        if(Utils.isSuccess(responseString)){
            //responseString.replace('\"', '\'');
            setToken(gson.fromJson(responseString, new TypeToken<Token>() {
            }.getType()));
            AuthenticationService.getToken().setPassword(password);
//            if (!isEmail) {}

               Response getCurrentUserResponse = new OkHttpClient().newCall(httpRequestMaker("/api/user/getUserByUserName/"+nameOrEmail, "get")).execute();
               token.setUser(gson.fromJson(getCurrentUserResponse.body().string(), new TypeToken<User>() {
               }.getType()));
        }
        return responseString;

    }

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        AuthenticationService.token = token;
    }
}
