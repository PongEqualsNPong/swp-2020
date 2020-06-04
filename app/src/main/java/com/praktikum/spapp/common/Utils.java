package com.praktikum.spapp.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {



    // parse a string to check if the response is a json with a 'success' key and which value it has
    public static boolean isSuccess(String responseString) {
        JsonParser parser = new JsonParser();
        // string to jsonelement, then
        JsonElement element = parser.parse(responseString);
        // jsonelement to jsonobject
        JsonObject resultAsJsonObject = element.getAsJsonObject();
        // finally jsonobject can use .get method and check success
        String isSuccess = resultAsJsonObject.get("success").getAsString();
        if (isSuccess.equals(1)) {
            return true;
        } else {
            return false;
        }
    }
}