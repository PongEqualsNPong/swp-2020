package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.common.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerJoinActivity extends AppCompatActivity  implements View.OnClickListener {
    //string from intent
    String username, pwd;
    //string from xml elems
    String givenName, givenPWD;
    //XML elems
    EditText uname, password;
    Button buttonConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serverjoin);

        Intent intent = getIntent();
        username = intent.getStringExtra("user name");
        pwd = intent.getStringExtra("password");

        //aasign XML
        uname = (EditText) findViewById(R.id.joinServer_inputUsername);
        password = (EditText) findViewById(R.id.serverjoin_inputPassword);

        buttonConfirm = (Button) findViewById(R.id.serverjoin_buttonConfirm);
        buttonConfirm.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        givenName = uname.getText().toString();
        givenPWD = password.getText().toString();
        try {
            postToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //I will try to write a hard-coded function. Without access to server, we can not validate if the function works or not
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void postToServer() throws IOException {

        String request = "fake an URL";

        //request body
        String POST_PARAMS = "{\"username\": \"" + givenName+"\", \"password\": \"" + givenPWD + "\"} " ;
        URL url = new URL(request);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        //set request header properties
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        //set response format type
        con.setRequestProperty("Accept", "application/json");
        //makes sure that connection is used to send content
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8")))
        {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            //will implement the alert notice once the access to server is possible for testing
        }
    }

}