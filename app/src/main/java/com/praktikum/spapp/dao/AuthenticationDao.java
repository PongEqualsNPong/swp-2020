package com.praktikum.spapp.dao;

import com.praktikum.spapp.models.Session;

public interface AuthenticationDao {


    Session logon(final String username, final String password);

}
