package com.praktikum.spapp.service;

import com.praktikum.spapp.models.Session;

public interface AuthenticationService {

    void logonServer(String nameOrEmail, String password);
    
}
