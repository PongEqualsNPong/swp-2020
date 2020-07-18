package com.praktikum.spapp.dao;

import com.google.gson.JsonObject;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.RegisterForm;
import com.praktikum.spapp.models.User;

import java.util.ArrayList;

public interface UserDao {

    String inviteUser(InviteForm form);

    void acceptInvite(RegisterForm form);

    ArrayList<User> fetchAll() throws ResponseException;

    void editUser(JsonObject data) throws ResponseException;

    String getUsernameByEmail(String email) throws ResponseException;

    User getUserByEmail(String email) throws ResponseException;

    String getUserEmailByUsername(String username) throws ResponseException;

    User getUserByUsername(String username) throws ResponseException;

    void deleteUserByEmailHard(String email);

    void deleteUserByEmail(String email);

    void deleteUserSelf();



    





}
