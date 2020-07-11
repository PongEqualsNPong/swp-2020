//package com.praktikum.spapp.service.internal;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.praktikum.spapp.common.HttpClient;
//import com.praktikum.spapp.common.Utils;
//import com.praktikum.spapp.models.Project;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import okhttp3.Request;
//import okhttp3.Response;
//
//import static com.praktikum.spapp.service.internal.Service.client;
//
//public class AppointmentsService {
//
//    public AppointmentsService() {
//        super();
//    }
//
//    public String appointmentCreate(String appointmentJson, int projectId) throws Exception {
//
//        Gson gson = new GsonBuilder().create();
//        JSONObject data = new JSONObject(appointmentJson);
//
//        Request request = HttpClient.httpRequestMaker("/api/projects/" + projectId + "/appointments", "post", data);
//        Response response = client.newCall(request).execute();
//
//        String responseString = response.body().string();
//
//        //these static methods must be called on every other service method u baboon
//        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
//        Utils.isSuccess(responseString);
//
//        if (isRefreshed) {
//            return appointmentCreate(appointmentJson, projectId);
//        } else {
//            return responseString;
//        }
//    }
//
//    public String appointmentFetch() throws Exception {
//
//        Request request = HttpClient.httpRequestMaker("/api/project/1/appointments", "get");
//        Response response = client.newCall(request).execute();
//
//        String responseString = response.body().string();
//
//        //these static methods must be called on every other service method u baboon
//        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
//        String successString = Utils.parseForJsonObject(responseString,"result");
//
//        if (isRefreshed) {
//            return appointmentFetch();
//        } else {
//            return new Gson().fromJson(successString, new TypeToken<ArrayList<Project>>() {
//            }.getType());
//        }
//    }
//
//    public String appointmentUpdate(String appointmentJson, int appointmentId) throws Exception {
//
//        Gson gson = new GsonBuilder().create();
//        JSONObject data = new JSONObject(appointmentJson);
//
//        Request request = HttpClient.httpRequestMaker("/api/appointments/" + appointmentId, "post", data);
//        Response response = client.newCall(request).execute();
//
//        String responseString = response.body().string();
//
//        //these static methods must be called on every other service method u baboon
//        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
//        Utils.isSuccess(responseString);
//
//        if (isRefreshed) {
//            return appointmentUpdate(appointmentJson,appointmentId);
//        } else {
//            return responseString;
//        }
//    }
//
//    public String appointmentDelete(int appointmentId) throws Exception {
//
//
//
//        Request request = HttpClient.httpRequestMaker("/api/appointments/" + appointmentId, "delete");
//        Response response = client.newCall(request).execute();
//
//        String responseString = response.body().string();
//
//        //these static methods must be called on every other service method u baboon
//        boolean isRefreshed = Utils.silentTokenRefresh(responseString);
//        Utils.isSuccess(responseString);
//
//        if (isRefreshed) {
//            return appointmentDelete(appointmentId);
//        } else {
//            return responseString;
//        }
//    }
//
//}
