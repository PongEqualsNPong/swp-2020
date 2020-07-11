package com.praktikum.spapp.service;

import com.praktikum.spapp.models.Session;

import java.io.IOException;

public interface AuthenticationService {

    Session logonServer(String nameOrEmail, String password) ;

}
