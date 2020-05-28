package com.praktikum.spapp.dto;

public class User {

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The email */
    private String email;

    private String role;

    private String userInfo;


    public User(String username, String password, String email){
        this.username =  username;
        this.password = password;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getEmail() { return this.email; }

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the password
     * @return
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Gets the username
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}

