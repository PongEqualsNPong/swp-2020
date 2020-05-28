package com.praktikum.spapp.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    URL url;
    public HttpURLConnection connection;

    public HttpClient(String url) throws IOException {
        this.url = new URL(url);
        connection = (HttpURLConnection)this.url.openConnection();
    }

    public void close() {
        connection.disconnect();
    }
}
