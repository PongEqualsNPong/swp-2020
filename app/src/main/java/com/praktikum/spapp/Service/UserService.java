package com.praktikum.spapp.Service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.User;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class UserService extends Service {


    public UserService() {
        super();
    }

    public ArrayList<User> fetchAllUsers() throws Exception {
        new Thread(() -> {
        }).start();

        Request request = HttpClient.httpRequestMaker("/api/user/fetchall", "get");

        Response response = client.newCall(request).execute();
        String responseString = Objects.requireNonNull(response.body()).string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = false;

        isRefreshed = Utils.silentTokenRefresh(responseString);

        String successString = Utils.parseForJsonObject(responseString, "result");

        if (isRefreshed) {
            return fetchAllUsers();
        } else {
            return new Gson().fromJson(successString, new TypeToken<ArrayList<User>>() {
            }.getType());
        }
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
                .header("Authorization", "Bearer " + AuthenticationService.getToken().getAccessToken())
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
                .put("forename", fname)
                .put("surname", lname)
                .put("username", username)
                .put("password", password)
                .put("studentNumber", studentID)
                .put("courseOfStudy", courseOfStudy)
                .put("examinationRegulations", examRegulation);

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

    public String editUser(EditUserForm editUserForm) throws Exception {
        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(editUserForm));
        Request request = HttpClient.httpRequestMaker("/api/user/editUser/", "post", data);
        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        return responseString;

    }





}