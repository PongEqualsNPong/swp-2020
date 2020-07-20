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
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    public UserDaoImpl() {
    }

    ;

    @Override
    public String inviteUser(InviteForm form) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(form));
        try {
            Response response = httpRequestMaker("/api/user/addUserInvitation", requestTypes.POST, data);
            String responseString = response.body().string();
            responseCheck(responseString);
            return Utils.parseForJsonObject(responseString, "InvitationLink");
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void acceptInvite(RegisterForm form, String invitationLinkUrl) throws ResponseException {
        JsonObject data = (JsonObject) new JsonParser().parse(new Gson().toJson(form));
        RequestBody body = RequestBody.create(data.toString(), JSON);
        try {
            Request request = new Request.Builder()
                    .url(api + "/api/user/byInvitation/" + invitationLinkUrl)

                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public ArrayList<User> fetchAll() throws ResponseException {
        try {
            Response response = httpRequestMaker("/api/user/fetchall", requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            String jsonString = Utils.parseForJsonObject(responseString, "result");
            return new Gson().fromJson(jsonString, new TypeToken<ArrayList<User>>() {
            }.getType());
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
        try {
            Response response = httpRequestMaker("/api/user/getUserByEmail/" + email, requestTypes.GET);
            String responseString = response.body().string();
            responseCheck(responseString);
            return new Gson().fromJson(responseString, new TypeToken<User>() {
            }.getType());
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public String getUserEmailByUsername(String username) throws ResponseException {
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
        try {
            Response response = httpRequestMaker("/api/user/getUserByUserName/" + username, requestTypes.GET);
            String responseString = response.body().string();

//            responseCheck(responseString);
            return new Gson().fromJson(responseString, new TypeToken<User>() {
            }.getType());
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void deleteUserByEmailHard(String email) throws ResponseException {
        JsonObject data = new JsonObject();
        data.addProperty("email", email);
        try {
            Response response = httpRequestMaker("/api/user/deleteUserByEmailHardDelete", requestTypes.DELETE, data);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    @Override
    public void deleteUserByEmail(String email) throws ResponseException {
        JsonObject data = new JsonObject();
        data.addProperty("email", email);
        try {
            Response response = httpRequestMaker("/api/user/deleteUserByEmail", requestTypes.DELETE, data);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }

    }

    @Override
    public void deleteUserSelf() throws ResponseException {
        try {
            Response response = httpRequestMaker("/api/user/deleteSelf", requestTypes.DELETE);
            String responseString = response.body().string();
            responseCheck(responseString);
        } catch (IOException e) {
            throw new ResponseException(e);
        }

    }
}
