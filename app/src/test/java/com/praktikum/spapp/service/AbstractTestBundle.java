package com.praktikum.spapp.service;

import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.service.internal.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractTestBundle {

    static String USERNAME_ADMIN = "admin";
    static String PASSWORD_ADMIN = "password";

    @BeforeAll
    public static void adminAuth() throws ResponseException {
        AuthenticationService adminAuth = new AuthenticationServiceImpl();
        adminAuth.logonServer(USERNAME_ADMIN, PASSWORD_ADMIN);
    }

}
