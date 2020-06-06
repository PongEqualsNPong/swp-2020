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
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;

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
        String invitationLink = "invitation@link";
        UserService userService = new UserService();

        switch (view.getId()){

            case R.id.checkInvite2_Confirm:

                try {
                    userService.checkInvitation(givenName,givenPWD,givenSN,invitationLink);
                } catch (JSONException e) {
                    e.printStackTrace();
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


}
