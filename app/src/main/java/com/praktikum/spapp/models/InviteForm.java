package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.Role;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InviteForm {

    private String email;
    private Long projectId;
    private Role role;
    private projectRights projectRights;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public projectRights getProjectRights() {
        return projectRights;
    }

    public void setProjectRights(projectRights projectRights) {
        this.projectRights = projectRights;
    }

    public enum projectRights {
        handler,
        processor
    }
}
