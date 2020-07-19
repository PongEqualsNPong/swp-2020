package com.praktikum.spapp.dao.internal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.dao.UserDao;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.RegisterForm;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.models.User;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public String inviteUser(InviteForm form) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(form));
        try {
            Response response = httpRequestMaker("/api/user/addUserInvitation", requestTypes.POST, data);
            String responseString = response.body().string();
            responseCheck(responseString);
            return Utils.parseForJsonObject(responseString, "InvitationLinkUrl");
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void acceptInvite(RegisterForm form) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(form));
        try {
            Response response = httpRequestMaker("/api/user/byInvitation/"  , requestTypes.POST, data);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public ArrayList<User> fetchAll() throws ResponseException {
        ArrayList<User> list = null;
        try {
            Response response = httpRequestMaker("/api/user/fetchall", requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            String jsonString = Utils.parseForJsonObject(responseString, "result");
            list = new Gson().fromJson(jsonString, new TypeToken<ArrayList<User>>() {
            }.getType());
            return list;
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void editUser(JsonObject data) throws ResponseException {
        try {
            Response response = httpRequestMaker("/api/user/editUser/", requestTypes.POST, data);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public String getUsernameByEmail(String email) throws ResponseException {
        String name = null;
        try {
            Response response = httpRequestMaker("/api/user/getUserNameByEmail/" + email, requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            return Utils.parseForJsonObject(responseString, "username");
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ResponseException {
        User user = null;
        try {
            Response response = httpRequestMaker("/api/user/getUserByEmail/" + email, requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            user = new Gson().fromJson(responseString, new TypeToken<User>() {
            }.getType());
            return user;
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public String getUserEmailByUsername(String username) throws ResponseException {
        String email = null;
        try {
            Response response = httpRequestMaker("/api/user/getUserEmailByUserName/" + username, requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            return Utils.parseForJsonObject(responseString, "email");
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public User getUserByUsername(String username) throws ResponseException {
        User user = null;
        try {
            Response response = httpRequestMaker("/api/user/getUserByUserName/" + username, requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            user = new Gson().fromJson(responseString, new TypeToken<User>() {
            }.getType());
            return user;
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void deleteUserByEmailHard(String email) {

    }

    @Override
    public void deleteUserByEmail(String email) {

    }

    @Override
    public void deleteUserSelf() {

    }
}
