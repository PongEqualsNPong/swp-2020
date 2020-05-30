package com.praktikum.spapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.praktikum.spapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckForInvite2 extends AppCompatActivity implements View.OnClickListener {

    //string from intent
    String userName, userPassword, studentNumber;
    //string from xml elems
    String givenName, givenPWD, givenSN;
    //XML elems
    EditText uname, pwd, matnr;
    Button buttonConfirm, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_for_invite2);


        Intent intent = getIntent();
        userName = intent.getStringExtra("invite Key");
        userPassword = intent.getStringExtra("first Name");
        studentNumber = intent.getStringExtra("last name");

        //assign XML
        uname = (EditText) findViewById(R.id.inputUserAccount);
        pwd = (EditText) findViewById(R.id.inputUserPassword);
        matnr = (EditText) findViewById(R.id.inputStudentNumber);

        buttonConfirm = (Button) findViewById(R.id.checkInvite2_Confirm);
        buttonCancel = (Button) findViewById(R.id.checkInvite2_Cancel);

        buttonConfirm.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {

        givenName = uname.getText().toString();
        givenPWD = pwd.getText().toString();
        givenSN = matnr.getText().toString();
        String server = "dummyserver@server";
        String invitationLink = "invitation@link";

        switch (view.getId()){

            case R.id.checkInvite2_Confirm:

                try {
                    startCheckForInvite3(server);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.checkInvite2_Cancel:
                uname.setText(" ");
                pwd.setText(" ");
                matnr.setText(" ");

                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startCheckForInvite3(String request) throws IOException {
        givenName = uname.getText().toString();
        givenPWD = pwd.getText().toString();
        givenSN = matnr.getText().toString();
        String inviteKey = "invite Key";

            //I will try to write a hard-coded function. Without access to server, we can not validate if the function works or not
            URL url = new URL(request);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            //set request header properties
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            //set response format type
            con.setRequestProperty("Accept", "application/json");
            //makes sure that connection is used to send content
            con.setDoOutput(true);
            //create request body
            String jsonInputString= "{ \"name\": \"" + givenName + "\", \"password\": \"" + givenPWD + "\", \"student number\": \"" + givenSN +  "\", \"inviteKey\": \"" + inviteKey+  "\"}";


            //read the response
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
