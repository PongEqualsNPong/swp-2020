package com.praktikum.spapp.Service;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.praktikum.spapp.common.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.ProtocolException;
import java.util.Scanner;

public class UserService {

    HttpClient userClient;

    // this stuff to abstract service class?
    public UserService() throws IOException {
//        super();
        this.userClient = new HttpClient("http://fakeUrl/user");
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public String getTestString() throws IOException {

        userClient.connection.setRequestMethod("GET");
        userClient.connection.setRequestProperty("Content-Type", "application/json");
        userClient.connection.setRequestProperty("charset", "utf-8");
        int responseCode = userClient.connection.getResponseCode();
        String jsonString = null;
        if (responseCode != 200)
            throw new RuntimeException("HttpResponseCode:  +responseCode");
        else {

            InputStream inStream = userClient.connection.getInputStream();
            jsonString = streamToString(inStream); // input stream to string
        }
        userClient.connection.disconnect();
        return jsonString;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String loginOnServer(String nameOrEmail, String password) throws ProtocolException, JSONException {
        userClient.connection.setRequestMethod("POST");
        userClient.connection.setRequestProperty("Content-Type", "application/json");
        userClient.connection.setRequestProperty("charset", "utf-8");
        userClient.connection.setDoOutput(true);


        // create json and convert to string
        JSONObject data = new JSONObject();
        data.put("username", nameOrEmail );
        data.put("password", password);

        String jsonStringToPost = data.toString();

        // output the string
        try(OutputStream os = userClient.connection.getOutputStream()) {
            byte[] input = jsonStringToPost.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(userClient.connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return responseLine;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
