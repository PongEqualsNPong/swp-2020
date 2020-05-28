package com.praktikum.spapp.common;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void httpClient() throws IOException {

        String urlToString = "https://jsonplaceholder.typicode.com/posts/1";
        HttpClient client =  new HttpClient(urlToString);
        assertNotNull(client.connection);
        client.close();
        try{
            client.connection.getResponseMessage();
        } catch(Exception e) {
            throw e;
        }
    }
}