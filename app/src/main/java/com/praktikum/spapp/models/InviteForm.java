package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.Role;

public class InviteForm {

    private String email;
    private int projectId;
    private Role role;
    private boolean isHandler;
    private boolean isProcessor;

    public InviteForm(String email, int projectId, Role role, boolean isHandler, boolean isProcessor) {
        this.email = email;
        this.projectId = projectId;
        this.role = role;
        this.isHandler = isHandler;
        this.isProcessor = isProcessor;
    }
    public InviteForm(){};

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

    public boolean isHandler() {
        return isHandler;
    }

    public void setHandler(boolean handler) {
        isHandler = handler;
    }

    public boolean isProcessor() {
        return isProcessor;
    }

    public void setProcessor(boolean processor) {
        isProcessor = processor;
    }
}
