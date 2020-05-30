package com.praktikum.spapp.models.enums;

public enum Role {

    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String toString() {
        switch (this)
        {
            case ROLE_USER:
                return "User";
            case ROLE_ADMIN:
                return "Admin";
            default:
                return "Asshole";
        }
    }
}


