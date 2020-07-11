package com.praktikum.spapp.service.internal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.dao.AuthenticationDao;
import com.praktikum.spapp.dao.internal.AbstractDao;
import com.praktikum.spapp.dao.internal.AuthenticationDaoImpl;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.service.AuthenticationService;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationServiceImpl extends Service implements AuthenticationService {

    AuthenticationDao dao;

    public AuthenticationServiceImpl() {
        dao = new AuthenticationDaoImpl();

    }

    public Session logonServer(String nameOrEmail, String password) {
        User user = null;
        Session session = dao.logon(nameOrEmail, password);

        try {
            Response response = dao.httpRequestMaker("api/user/getUserByUserName/" + nameOrEmail, "get");
            user = new Gson().fromJson(response.body().string(), new TypeToken<User>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setCurrentUser(user);
        return session;
    }
}
