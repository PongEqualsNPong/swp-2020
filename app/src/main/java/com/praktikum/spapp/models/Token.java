package com.praktikum.spapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable {


    public String tokenType;
    public String accessToken;
    public String success;

    public Token(String tokenType, String accessToken, String success) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.success = success;
    }

    public Token() {

    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSuccess() {
        return success;
    }
}
