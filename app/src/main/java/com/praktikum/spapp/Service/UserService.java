package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserService {

    OkHttpClient client = new OkHttpClient();

    //for now do singleton pattern
    String authToken;

    String ourAPI = "https//outAPI.com";

    // this in abstract Service class?
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public UserService() {}
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
        // make request
        Request request = new Request.Builder()
                .url(ourAPI)
                .post(requestBody)
                .build();

        // get the response as a string
        try(Response response = client.newCall(request).execute()) {
            // create the response string
            String responseString = response.body().string();
            //
            JSONObject  someString =  new JSONObject(responseString);
            authToken = someString.getString("accessToken");

            return responseString;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }

    public String fetchAllUsers() throws IOException {
        Request request = new Request.Builder()
                .url(ourAPI)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
