package com.praktikum.spapp.Service;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends Service {

    private final static String TAG = "UserService";
    static String accessToken;

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
    public Token loginOnServer(String nameOrEmail, String password) throws IOException, JSONException {

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
        Token tokenJson = gson.fromJson(responseString, listType);

        System.out.print(tokenJson.getAccessToken());
        UserService.accessToken = tokenJson.getAccessToken();
        return tokenJson;
    }

    public ArrayList<User> fetchAllUsers() throws IOException {
        new Thread(() -> {
        }).start();
        String empty = "";
        Request request = new Request.Builder()
                .url(api + "/api/user/fetchall")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTYwNzY5MSwiZXhwIjoxNTkxNjk0MDkxfQ.eL-F5RuYkPIGU1Xi_iDe7AS7V1yaB7rOnWrYagKfKogRRf9661R7s9sxFh0xAD2Rl5mAMxPLj14j-EbvDmoR6A")
                .header("Access-Control-Allow-Origin", "*")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
//        System.out.println(responseString);


        if (Utils.isSuccess(responseString)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseString);
            JsonObject resultAsJsonObject = element.getAsJsonObject();
            JsonElement isSuccess = resultAsJsonObject.get("result");
            String successString = isSuccess.toString();

            Gson gson = new Gson();
            //
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            ArrayList<User> userArrayList = gson.fromJson(successString, listType);
            return userArrayList;

        }
        return null;



    }

    public String addUserInvitation(InviteForm inviteForm) throws JSONException, IOException {

        JSONObject data = new JSONObject()
                .put("email", inviteForm.getEmail())
                .put("projectId", inviteForm.getProjectId())
                .put("role", inviteForm.getRole().toString());

        if (inviteForm.isHandler()) {
            data.put("projectRights", "handler");
        }
        if (inviteForm.isProcessor()) {
            data.put("projectRights", "processor");
        }
        String dataString = data.toString();

        RequestBody requestBody = RequestBody.create(dataString, JSON);
        Request request = new Request.Builder()
                .url(api)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}