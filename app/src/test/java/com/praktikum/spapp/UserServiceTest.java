package com.praktikum.spapp;

import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UserServiceTest {

//    @Test
//    public void loginOnServerTest() throws JSONException, IOException {
//
//        String nameAdmin = "admin";
//        String passwordAdmin = "password";
//        String wrongPasswordAdmin = "wrong";
//
//        UserService userService = new UserService();
//
//        Token token = userService.loginOnServer(nameAdmin, passwordAdmin);
//        Assert.assertNotNull(token);
//        Assert.assertEquals(token.getSuccess(), "1");
//        Token token2 = userService.loginOnServer(nameAdmin, wrongPasswordAdmin);
//
//    }


    @Test
    public void fetchAllUsersTest() throws IOException {
        UserService userService = new UserService();
        ArrayList<User> userArrayList = (ArrayList<User>) userService.fetchAllUsers();
        for (User element : userArrayList) {
        }
    }
}