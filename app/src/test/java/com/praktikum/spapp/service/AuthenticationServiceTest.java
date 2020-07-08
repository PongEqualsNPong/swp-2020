package com.praktikum.spapp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    String USERNAME_ADMIN = "admin";
    String PASSWORD_ADMIN = "password";

    String USERNAME_USER = "user";
    String PASSWORD_USER = "password";

    String PASSWORD_NOONE = "incorrect";

    @Test
    void loginOnServerAdmin() throws Exception {

        AuthenticationService.loginOnServer(USERNAME_ADMIN, PASSWORD_ADMIN);
        assertNotNull(AuthenticationService.getToken().getAccessToken());
        assertEquals(USERNAME_ADMIN, AuthenticationService.getToken().getCurrentUser().getUsername());

    }

    @Test
    void loginOnServerFalse() throws Exception {

        AuthenticationService.loginOnServer(USERNAME_USER, PASSWORD_NOONE);
        assertNull(AuthenticationService.getToken().getAccessToken());
    }


}