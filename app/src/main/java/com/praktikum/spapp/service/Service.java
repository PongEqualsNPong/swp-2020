package com.praktikum.spapp.service;

        import okhttp3.MediaType;
        import okhttp3.OkHttpClient;


public abstract class Service {

    public static OkHttpClient client;


<<<<<<< Updated upstream:app/src/main/java/com/praktikum/spapp/service/Service.java
    public static final String api = "http://192.168.178.176:8081";
=======
    public static final String api = "http://192.168.2.100:8081";
>>>>>>> Stashed changes:app/src/main/java/com/praktikum/spapp/Service/Service.java

    // need this for okhttp
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    // constructor
    public Service() {
        client = new OkHttpClient();
    }
}
