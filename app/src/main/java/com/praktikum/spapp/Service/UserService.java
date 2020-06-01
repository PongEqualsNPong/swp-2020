package com.praktikum.spapp.Service;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.models.AuthenticatorSingleton;
import com.praktikum.spapp.models.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends Service {

    private final static String TAG = "UserService";
    //
    public UserService() {
        super();
    }

    public String testAPI() throws IOException, JSONException {
        Request request = new Request.Builder().url(api).build();
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        JsonObject someJSON = new JsonParser().parse(responseString).getAsJsonObject();
        String result = someJSON.get("status").getAsString();
        System.out.println(responseString);
        System.out.println(result);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loginOnServer(String nameOrEmail, String password) throws IOException, JSONException {

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
                .url(api)
                .post(requestBody)
                .build();

        // get the response as a string
        try (Response response = client.newCall(request).execute()) {
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

    public List<User> fetchAllUsers() throws IOException {
        String empty = "";
        Request request = new Request.Builder()
                .url(api)
                .header("Authorization", "Bearer sdf")
                .post(RequestBody.create(empty,JSON))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        System.out.println(responseString);


        Gson gson =  new Gson();
        //
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> userArrayList = gson.fromJson(responseString, listType);
        if(userArrayList == null) {
            Log.d(TAG, "fetchAllUsers: ");;
        }
        return userArrayList;
    }
}