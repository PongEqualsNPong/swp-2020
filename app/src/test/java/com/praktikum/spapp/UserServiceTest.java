package com.praktikum.spapp;

import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.User;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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


    @Test
    public void fetchAllUsersTest() throws IOException {
        UserService userService = new UserService();
        ArrayList<User> userArrayList = (ArrayList<User>) userService.fetchAllUsers();
        for(User element : userArrayList ){
        System.out.println(element.getUsername());}
    }


}