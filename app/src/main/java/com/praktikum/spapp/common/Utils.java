package com.praktikum.spapp.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.praktikum.spapp.service.internal.AuthenticationServiceImpl;

import java.io.IOException;

public class Utils {

    // parse a string to check if the response is a json with a 'success' key and which value it has
//    public static boolean silentTokenRefresh(String responseString) throws Exception {
//
//        // create a parser instance
//        JsonParser parser = new JsonParser();
//        // string to jsonelement, then
//        JsonElement element = parser.parse(responseString);
//        System.out.println(responseString);
//
//        // jsonelement to jsonobject
//        JsonObject resultAsJsonObject = element.getAsJsonObject();
//        // finally jsonobject can use .get method and check success
//
//        try {
//            String status = resultAsJsonObject.get("status").getAsString();
//            switch (status) {
//
//                case "401":
//                    AuthenticationServiceImpl.loginOnServer(AuthenticationServiceImpl.getSession().getCurrentUser().getUsername(), AuthenticationServiceImpl.getSession().getPassword());
//                    return true;
//
//                case "403":
//            }
//        } catch (NullPointerException | IOException e) {
//            //do nothing
//        }
//        String isSuccess = resultAsJsonObject.get("success").getAsString();
//
//        return false;
//    }

    public static String parseForJsonObject(String responseString, String jsonObject) {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseString);
        JsonObject resultAsJsonObject = element.getAsJsonObject();
        JsonElement bElement = resultAsJsonObject.get(jsonObject);
        return bElement.toString();
    }

    public static boolean isSuccess(String responseString) throws Exception {

        String isSuccess = parseForJsonObject(responseString, "success");
        if (isSuccess.contains("1")) {
            return true;
        } else {
            return false;
        }
    }

}
