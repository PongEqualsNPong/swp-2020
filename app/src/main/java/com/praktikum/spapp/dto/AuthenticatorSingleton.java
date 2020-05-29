package com.praktikum.spapp.dto;

import com.google.gson.annotations.SerializedName;

public class AuthenticatorSingleton {

    private static AuthenticatorSingleton instance;

    private AuthenticatorSingleton(){};

    public static AuthenticatorSingleton getInstance() {
        if(AuthenticatorSingleton.instance == null) {
            AuthenticatorSingleton.instance = new AuthenticatorSingleton();
        }
        return AuthenticatorSingleton.instance;
    }
    @SerializedName("accessToken")
    public String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
