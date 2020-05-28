package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.praktikum.spapp.dto.Project;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProjectService {

    OkHttpClient client = new OkHttpClient();
    String ourAPI = "https//outAPI.com";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public ProjectService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String projectInit(Project project) throws JSONException, IOException {

        String data = new JSONObject()
                .put("name", project.getName())
                .put("description", project.getDescription())
                .toString();

        RequestBody requestBody = RequestBody.create(data, JSON);
        Request request = new Request.Builder()
                .url(ourAPI)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            // create the response string
            String responseString = response.body().string();
            return responseString;
        }
    }


}
