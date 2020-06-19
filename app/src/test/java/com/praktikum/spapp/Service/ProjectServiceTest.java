package com.praktikum.spapp.Service;

import com.praktikum.spapp.models.Project;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.praktikum.spapp.Service.AuthenticationService.loginOnServer;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {
    String USERNAME_ADMIN = "admin";
    String PASSWORD_ADMIN = "password";

    @Test
    void projectCreate() throws Exception {

        AuthenticationService.loginOnServer(USERNAME_ADMIN, PASSWORD_ADMIN);

        Project projectOne = new Project();
        projectOne.setName("nazis klatschen");
        projectOne.setDescription("wir verhauen faschos");
        String result = new ProjectService().projectCreate(projectOne);
        assertTrue(result.contains("success:1"));

    }

    @Test
    void fetchAllProjects() throws IOException {
        AuthenticationService.loginOnServer(USERNAME_ADMIN, PASSWORD_ADMIN);

        ArrayList<Project> result = new ProjectService().fetchAllProjects();
        assertTrue(result.size() > 0);
        assertEquals("Test_Projekt", result.get(0).getName());

    }

    @Test
    void fetchProjectsOnlyFromUser() {
    }
}