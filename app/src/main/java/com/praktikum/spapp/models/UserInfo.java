package com.praktikum.spapp.models;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private int id;

    private String forename;

    private String surename;

    private int studentNumber;

    private String courseOfStudy;

    public UserInfo(int id, String forename, String surename, int studentNumber, String courseOfStudy, String examinationRegulations) {
        this.id = id;
        this.forename = forename;
        this.surename = surename;
        this.studentNumber = studentNumber;
        this.courseOfStudy = courseOfStudy;
        this.examinationRegulations = examinationRegulations;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public String getExaminationRegulations() {
        return examinationRegulations;
    }

    public void setExaminationRegulations(String examinationRegulations) {
        this.examinationRegulations = examinationRegulations;
    }

    private String examinationRegulations;

    public UserInfo(int id, String forename, String surename, int studentNumber) {
        this.id = id;
        this.forename = forename;
        this.surename = surename;
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
