package com.praktikum.spapp.service;

import com.praktikum.spapp.models.User;
import org.junit.Test;

import java.util.ArrayList;

public class UserServiceTest {


    @Test
    public void fetchAllUsersTest() throws Exception {

        AuthenticationService.loginOnServer("admin", "password");
//        AuthenticationService.getToken().setAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTU3MzIzNCwiZXhwIjoxNTkxNjU5NjM0fQ.b4hxTAeww8biGC-MjHbqBiCTSN-UL_5cWxSiWWrxRoqqFMAlsARTgbcbHD8iyAjblvJv5QwUnkSMKHJvdFHZ1A");

        UserService userService = new UserService();
        ArrayList<User> userArrayList = userService.fetchAllUsers();

    }
}