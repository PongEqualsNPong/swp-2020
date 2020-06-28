package com.praktikum.spapp.Service;

import android.accounts.AuthenticatorException;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.Token;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {
    String USERNAME_ADMIN = "admin";
    String USERNAME_USER = "user";
    String PASSWORD_ADMIN = "password";

    @Test
    void projectCreate() throws Exception {

        AuthenticationService.loginOnServer(USERNAME_ADMIN, PASSWORD_ADMIN);
        AuthenticationService.getToken().setAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTU3MzIzNCwiZXhwIjoxNTkxNjU5NjM0fQ.b4hxTAeww8biGC-MjHbqBiCTSN-UL_5cWxSiWWrxRoqqFMAlsARTgbcbHD8iyAjblvJv5QwUnkSMKHJvdFHZ1A");


        Project projectOne = new Project();
        projectOne.setName("nazis klatschen");
        projectOne.setDescription("wir verhauen faschos");
        String result = new ProjectService().projectCreate(projectOne);
        assertTrue(result.contains("success:1"));

    }

    @Test
    void fetchAllProjects() throws Exception {

        AuthenticationService.loginOnServer(USERNAME_USER, PASSWORD_ADMIN);

        ArrayList<Project> result = new ProjectService().fetchAllProjects();

    }

    @Test
    void fetchAllProjectsDenied() throws Exception {
        AuthenticationService.loginOnServer(USERNAME_USER,PASSWORD_ADMIN);

        ArrayList<Project> result = new ProjectService().fetchAllProjects();
        assertTrue(result.size() > 0);
        assertEquals("Test_Projekt", result.get(0).getName());
    }

    @Test
    void fetchProjectsOnlyFromUser() {
    }
}