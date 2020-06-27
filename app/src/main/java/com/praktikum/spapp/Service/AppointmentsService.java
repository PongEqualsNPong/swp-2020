package com.praktikum.spapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.HttpClient;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.models.Project;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Request;
import okhttp3.Response;

import static com.praktikum.spapp.Service.Service.client;

public class AppointmentsService {

    public AppointmentsService() {
        super();
    }

    public String appointmentCreate(Appointment appointment) throws Exception {

        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(appointment));

        Request request = HttpClient.httpRequestMaker("/api/project/1/appointments", "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return appointmentCreate(appointment);
        } else {
            return responseString;
        }
    }

    public String appointmentFetch() throws Exception {

        Request request = HttpClient.httpRequestMaker("/api/project/1/appointments", "get");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        String successString = Utils.parseForJsonObject(responseString,"result");

        if (isRefreshed) {
            return appointmentFetch();
        } else {
            return new Gson().fromJson(successString, new TypeToken<ArrayList<Project>>() {
            }.getType());
        }
    }

    public String appointmentUpdate(Appointment appointment) throws Exception {

        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(appointment));

        Request request = HttpClient.httpRequestMaker("/api/appointments/{appointment id}", "post", data);
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return appointmentCreate(appointment);
        } else {
            return responseString;
        }
    }

    public String appointmentDelete(Appointment appointment) throws Exception {

        Gson gson = new GsonBuilder().create();
        JSONObject data = new JSONObject(gson.toJson(appointment));

        Request request = HttpClient.httpRequestMaker("/api/appointments/{appointment id}", "delete");
        Response response = client.newCall(request).execute();

        String responseString = response.body().string();

        //these static methods must be called on every other service method u baboon
        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
        Utils.isSuccess(responseString);

        if (isRefreshed) {
            return appointmentCreate(appointment);
        } else {
            return responseString;
        }
    }

}
