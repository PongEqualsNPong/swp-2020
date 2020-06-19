package com.praktikum.spapp.common;

import com.praktikum.spapp.Service.AuthenticationService;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.praktikum.spapp.Service.Service.*;

public class HttpClient {

    public static Request httpRequestMaker(String path, String method, Map<String, String> map) throws IOException {

        RequestBody body;
            JSONObject data = new JSONObject(map);
            body = RequestBody.create(data.toString(), JSON);


        switch (method) {
            case "put":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                        .put(body)
                        .build();

            case "post":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                        .post(body)
                        .build();


            case "get":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                        .get()
                        .build();

            case "delete":
                return new Request.Builder()
                        .url(api + path)
                        .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
                        .delete()
                        .build();

        }
        return null;
    }

    public static Request httpRequestMaker(String path, String method) throws IOException {
        Map map = new HashMap<String, String>();

        return httpRequestMaker(path, method, map);

    }
}
