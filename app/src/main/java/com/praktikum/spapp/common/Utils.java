package com.praktikum.spapp.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.praktikum.spapp.Service.AuthenticationService;

import java.io.IOException;

public class Utils {

    // parse a string to check if the response is a json with a 'success' key and which value it has
    public static boolean silentTokenRefresh(String responseString) throws IOException {

        // create a parser instance
        JsonParser parser = new JsonParser();
        // string to jsonelement, then
        JsonElement element = parser.parse(responseString);
        // jsonelement to jsonobject
        JsonObject resultAsJsonObject = element.getAsJsonObject();
        // finally jsonobject can use .get method and check success
        String isSuccess = resultAsJsonObject.get("success").getAsString();
        if (!(isSuccess.equals("1"))) {
            AuthenticationService.loginOnServer(AuthenticationService.getToken().getUsername(), AuthenticationService.getToken().getPassword());
            return true;

        }
        return false;
    }

    public static String jsonCleaner(String responseString) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseString);
        JsonObject resultAsJsonObject = element.getAsJsonObject();
        JsonElement isSuccess = resultAsJsonObject.get("result");
        return isSuccess.toString();
    }

    public static String jsonCleaner(String responseString) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseString);
        JsonObject resultAsJsonObject = element.getAsJsonObject();
        JsonElement isSuccess = resultAsJsonObject.get("result");
        return isSuccess.toString();
    }
}
