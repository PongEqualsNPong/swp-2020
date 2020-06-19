package com.praktikum.spapp.common;

import com.praktikum.spapp.Service.AuthenticationService;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;


import static com.praktikum.spapp.Service.Service.*;

public class HttpClient {

    public static Request httpRequestMaker(String path, String method, JSONObject json) {

        RequestBody body = RequestBody.create(json.toString(), JSON);

        switch (method) {
            case "put":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", AuthenticationService.getToken().getTokenType() + " " + AuthenticationService.getToken().getAccessToken())
                        .put(body)
                        .build();

            case "post":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", AuthenticationService.getToken().getTokenType() + " " + AuthenticationService.getToken().getAccessToken())
                        .post(body)
                        .build();


            case "get":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", AuthenticationService.getToken().getTokenType() + " " + AuthenticationService.getToken().getAccessToken())
                        .get()
                        .build();

            case "delete":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", AuthenticationService.getToken().getTokenType() + " " + AuthenticationService.getToken().getAccessToken())
                        .delete()
                        .build();
        }
        return null;
    }

    public static Request httpRequestMaker(String path, String method) {

        return httpRequestMaker(path, method, new JSONObject());

    }
}
