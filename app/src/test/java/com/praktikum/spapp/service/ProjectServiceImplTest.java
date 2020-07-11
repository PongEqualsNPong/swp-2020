package com.praktikum.spapp.service;

import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.service.internal.AuthenticationServiceImpl;
import com.praktikum.spapp.service.internal.ProjectServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceImplTest {
    String USERNAME_ADMIN = "admin";
    String USERNAME_USER = "user";
    String PASSWORD_ADMIN = "password";

    @Test
    void projectCreate() throws Exception {

        AuthenticationServiceImpl.loginOnServer(USERNAME_ADMIN, PASSWORD_ADMIN);
        AuthenticationServiceImpl.getSession().setAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MTU3MzIzNCwiZXhwIjoxNTkxNjU5NjM0fQ.b4hxTAeww8biGC-MjHbqBiCTSN-UL_5cWxSiWWrxRoqqFMAlsARTgbcbHD8iyAjblvJv5QwUnkSMKHJvdFHZ1A");


        Project projectOne = new Project();
        projectOne.setName("nazis klatschen");
        projectOne.setDescription("wir verhauen faschos");
        String result = new ProjectServiceImpl().projectCreate(projectOne);
        assertTrue(result.contains("success:1"));

    }

    @Test
    void fetchAllProjects() throws Exception {

        AuthenticationServiceImpl.loginOnServer(USERNAME_USER, PASSWORD_ADMIN);

        ArrayList<Project> result = new ProjectServiceImpl().fetchAllProjects();

    }

    @Test
    void fetchAllProjectsDenied() throws Exception {
        AuthenticationServiceImpl.loginOnServer(USERNAME_USER,PASSWORD_ADMIN);

        ArrayList<Project> result = new ProjectServiceImpl().fetchAllProjects();
        assertTrue(result.size() > 0);
        assertEquals("Test_Projekt", result.get(0).getName());
    }

    @Test
    void fetchProjectsOnlyFromUser() {
    }
}