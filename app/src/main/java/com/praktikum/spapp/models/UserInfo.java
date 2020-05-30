package com.praktikum.spapp.models;

public class UserInfo {

    private String id;

    private String forename;

    private String surename;

    private String studentNumber;

    public UserInfo(String id, String forename, String surename, String studentNumber) {
        this.id = id;
        this.forename = forename;
        this.surename = surename;
        this.studentNumber = studentNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
