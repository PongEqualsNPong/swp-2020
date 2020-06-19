package com.praktikum.spapp.models;


import java.io.Serializable;

public class Token implements Serializable {


    private String tokenType;
    private String accessToken;
    private String success;
    private String username;
    private String password;



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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
