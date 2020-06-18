package com.praktikum.spapp.common;

import com.praktikum.spapp.Service.AuthenticationService;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static com.praktikum.spapp.Service.Service.api;
import static com.praktikum.spapp.Service.Service.client;

public class HttpClient {

    static String httpRequestMaker(String path, enum method, String body) throws IOException {

        Request request = new Request.Builder()
                .url(api + "/api/project")
                .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                .build();

        switch
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
