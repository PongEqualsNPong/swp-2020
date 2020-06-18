package com.praktikum.spapp.Service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.User;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class UserService extends Service {

    private final static String TAG = "UserService";

    //
    public UserService() {
        super();
    }

    public ArrayList<User> fetchAllUsers() throws IOException {
        new Thread(() -> {
        }).start();

        Request request = new Request.Builder()
                .url(api + "/api/user/fetchall")
                .header("Authorization", "Bearer " + AuthenticationService.getTokten().getAccessToken())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();


        if (Utils.isSuccess(responseString)) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseString);
            JsonObject resultAsJsonObject = element.getAsJsonObject();
            JsonElement isSuccess = resultAsJsonObject.get("result");
            String successString = isSuccess.toString();
            System.out.print(successString);
            Gson gson = new Gson();
            //
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            return gson.fromJson(successString, listType);
        }
        return null;


    }

    public String addUserInvitation(InviteForm inviteForm) throws JSONException, IOException {

        JSONObject data = new JSONObject()
                .put("email", inviteForm.getEmail())
                .put("projectId", inviteForm.getProjectId())
                .put("role", inviteForm.getRole().toString())
                .put("projectRights", inviteForm.getProjectRights());

        String dataString = data.toString();

        RequestBody requestBody = RequestBody.create(dataString, JSON);
        Request request = new Request.Builder()
                .url(api + "/api/user/addUserInvitation")
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTYxNzA4MCwiZXhwIjoxNTkxNzAzNDgwfQ.oyHirz6PtALYSG4tbkGg1KI9txWiqs111qI6eKBnn5jrksVUPbJBb7C8TVbVqGD_ijevdCpkroY-qfw9bKCJ7Q")
                .header("Access-Control-Allow-Origin", "*")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String checkInvitation(String fname,
                                  String lname,
                                  String password,
                                  String studentID,
                                  String inviteKey,
                                  String username,
                                  String courseOfStudy,
                                  String examRegulation) throws JSONException, IOException {

        JSONObject data = new JSONObject()
                .put("first name", fname)
                .put("last name", lname)
                .put("username", username)
                .put("password", password)
                .put("student ID", studentID)
                .put("course of study", courseOfStudy)
                .put("exam regulation", examRegulation);

        String dataString = data.toString();

        RequestBody requestBody = RequestBody.create(dataString, JSON);
        Request request = new Request.Builder()
                .url(api + "/api/user/byInvitation/" + inviteKey)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();

    }


}