package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.Role;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InviteForm {

    private String email;
    private int projectId;
    private Role role;
    private String projectRights;

    public InviteForm(String email, int projectId, Role role, String projectRights) {
        this.email = email;
        this.projectId = projectId;
        this.role = role;
        this.projectRights = projectRights;
    }
    public InviteForm(){
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProjectRights() {
        return projectRights;
    }

    public void setProjectRights(String projectRights) {
        this.projectRights = projectRights;
    }
}
