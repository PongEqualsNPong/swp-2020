package com.praktikum.spapp.service;

import com.google.gson.JsonObject;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.service.internal.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserServiceImplTest extends AbstractTestBundle {

    UserService adminService = new UserServiceImpl(SessionManager.getSession());

    @Test
    public void testFetchAllUsers() throws ResponseException {
        ArrayList<User> adminsList = adminService.fetchAll();
        assertTrue(adminsList.size() == 7);
    }

    @Test
    public void testEditUser() throws ResponseException {
        String newName = "Gorgeous Cow";
        String dbModEmail = "test_mod@email.com";

        JsonObject data = new JsonObject();
        data.addProperty("username", newName);
        data.addProperty("userToEditByEmail", dbModEmail);
        adminService.editUser(data);

        User result = adminService.getUserByEmail(dbModEmail);

        assertEquals(newName, result.getUsername());

        // cleanup
        data = new JsonObject();
        data.addProperty("username", "t_mod");
        data.addProperty("userToEditByEmail", dbModEmail);

        adminService.editUser(data);
        result = adminService.getUserByEmail(dbModEmail);

        assertEquals("t_mod", result.getUsername());


    }

}