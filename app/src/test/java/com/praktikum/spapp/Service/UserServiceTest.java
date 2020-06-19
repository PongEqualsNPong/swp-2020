package com.praktikum.spapp.Service;

import com.praktikum.spapp.models.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UserServiceTest {


    @Test
    public void fetchAllUsersTest() throws IOException {
        UserService userService = new UserService();
        ArrayList<User> userArrayList = userService.fetchAllUsers();

    }
}