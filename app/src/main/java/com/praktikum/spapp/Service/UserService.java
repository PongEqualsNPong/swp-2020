package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.praktikum.spapp.dto.AuthenticatorSingleton;
import okhttp3.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserService extends Service{

    //
    public UserService() {
        super();
    }

    public String testAPI() throws IOException, JSONException {
        Request request = new Request.Builder().url(api).build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JsonObject someJSON =  new JsonParser().parse(responseString).getAsJsonObject();
        String result = someJSON.get("status").getAsString();
        System.out.println(responseString);
        System.out.println(result);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loginOnServer(String nameOrEmail, String password) throws IOException, JSONException {

        // create jsonString GSON by map
        Map<String,String> map = new HashMap<String, String>();
        map.put("username", nameOrEmail);
        map.put("password", password);
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(map);


        // create request body
        RequestBody requestBody = RequestBody.create(jsonString,JSON);
        // make request
        Request request = new Request.Builder()
                .url(api)
                .post(requestBody)
                .build();

        // get the response as a string
        try(Response response = client.newCall(request).execute()) {
            // create the response string
            String responseString = response.body().string();
            // convert string to json object
            JsonParser parser = new JsonParser();
            JsonObject result = parser.parse(responseString).getAsJsonObject();


            // parse the auth token
            AuthenticatorSingleton.getInstance().setAccessToken(gson.fromJson(responseString, String.class));
            return responseString;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String fetchAllUsers() throws IOException {
        Request request = new Request.Builder()
                .url(api)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
