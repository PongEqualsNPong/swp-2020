package com.praktikum.spapp.Service;

        import okhttp3.MediaType;
        import okhttp3.OkHttpClient;


public abstract class Service {

    public static OkHttpClient client;


    public static final String api = "http://192.168.2.100:8081";

    // need this for okhttp
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    // constructor
    public Service() {
        client = new OkHttpClient();
    }
}
