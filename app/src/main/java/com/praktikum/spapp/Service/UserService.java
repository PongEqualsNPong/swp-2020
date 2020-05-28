package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserService {

    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public UserService() throws IOException {

    }
//        Request request = new Request.Builder()
//                .url("https://jsonplaceholder.typicode.com/")
//                .build();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loginOnServer(String nameOrEmail, String password) throws IOException, JSONException {

        // create json and convert to string
        String data = new JSONObject()
                .put("username", nameOrEmail )
                .put("password", password)
                .toString();

        // create request body
        RequestBody requestBody = RequestBody.create(data,JSON);
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/")
                .post(requestBody)
                .build();

        // get the response as a string
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }
}
