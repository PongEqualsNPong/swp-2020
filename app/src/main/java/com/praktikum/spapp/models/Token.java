package com.praktikum.spapp.models;


import java.io.Serializable;

public class Token implements Serializable {


    private String tokenType;
    private String accessToken;
    private String success;
    private String password;
    private User currentUser;


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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
