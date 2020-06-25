package com.praktikum.spapp.models;

import com.praktikum.spapp.models.enums.Role;

public class EditUserForm {

    String userEditByEmail;
//    Role role;
    String email;
    String username;
    String forename;
    String surname;
    int studentNumber;
    //needs old and new password fields
    //consider more appropriate types like enum
    String courseOfStudy;
    String examinationRegulations;


    // getters and setters
    public String getUserEditByEmail() {
        return userEditByEmail;
    }

    public void setUserEditByEmail(String userEditByEmail) {
        this.userEditByEmail = userEditByEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
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
}
