package com.praktikum.spapp.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public abstract class Service {

    OkHttpClient client;

    final String api = "https://api.solaimani.de";

    // need this for okhttp
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    // constructor
    public Service(){
        client = new OkHttpClient();
    }



}
