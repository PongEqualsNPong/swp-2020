package com.praktikum.spapp.service;

import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.service.internal.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceImplTest {

    String USERNAME_ADMIN = "admin";
    String PASSWORD_ADMIN = "password";

    String PASSWORD_NOONE = "incorrect";

    @Test
    public void testLogonAdmin() {
        AuthenticationService service = new AuthenticationServiceImpl();
        service.logonServer(USERNAME_ADMIN, PASSWORD_ADMIN);

        assertEquals(USERNAME_ADMIN, SessionManager.getSession().getCurrentUsername());

    }

}