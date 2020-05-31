package com.praktikum.spapp;

import com.praktikum.spapp.Service.UserService;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class UserServiceTest {

    public void testAPI() throws IOException, JSONException {
        UserService userService = new UserService();
        System.out.println(userService.testAPI());
    }

    @Test
    public void loginOnServerTest() throws JSONException, IOException {

        String nameAdmin = "admin";
        String passwordAdmin = "admin";
        String wrongPasswordAdmin = "wrong";

        UserService userService = new UserService();
        System.out.println(userService.loginOnServer(nameAdmin, passwordAdmin));




    }


}