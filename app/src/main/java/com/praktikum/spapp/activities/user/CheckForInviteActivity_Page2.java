package com.praktikum.spapp.activities.user;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.service.UserService;
import com.praktikum.spapp.common.Utils;

import java.io.IOException;

public class CheckForInviteActivity_Page2 extends AppCompatActivity implements View.OnClickListener {

    //string from intent
    String userName, userPassword, studentNumber, firstName, lastName, invitationLink;
    //string from xml elems
    String givenName, givenPWD, givenSN, givenMJ, givenPO;
    //XML elems
    EditText uname, pwd, matnr, major, po;
    Button buttonConfirm, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_for_invite2);


        Intent intent = getIntent();
        userName = intent.getStringExtra("invite Key");
        userPassword = intent.getStringExtra("first Name");
        studentNumber = intent.getStringExtra("last name");
        firstName = intent.getStringExtra("first_name");
        lastName = intent.getStringExtra("last_name");
        invitationLink = intent.getStringExtra("invite_key");

        //assign XML
        uname = (EditText) findViewById(R.id.inputUserAccount);
        pwd = (EditText) findViewById(R.id.inputUserPassword);
        matnr = (EditText) findViewById(R.id.inputStudentNumber);
        major = (EditText) findViewById(R.id.inputMajor);
        po = (EditText) findViewById(R.id.inputPO);

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
        givenMJ = major.getText().toString();
        givenPO = po.getText().toString();


        switch (view.getId()){

            case R.id.checkInvite2_Confirm:

                new Thread(() -> {
                    try {
                        UserService userService = new UserService();
                        String res = userService.checkInvitation(firstName, lastName, givenPWD, givenSN, invitationLink, givenName, givenMJ, givenPO);
                        if(Utils.isSuccess(res)) {
                            runOnUiThread(() -> Snackbar.make(view, "Accepted the Invitation, now get out", Snackbar.LENGTH_LONG).show());
                        }
                        else {
                            runOnUiThread(() ->  Snackbar.make(view, "something happened", Snackbar.LENGTH_LONG).show());
                        }

                    } catch (IOException e) {
                        runOnUiThread(() ->Snackbar.make(view, e.getMessage().toString(), Snackbar.LENGTH_LONG).show() );
                    } catch (Exception e) {
                        runOnUiThread(() ->Snackbar.make(view, e.getMessage().toString(), Snackbar.LENGTH_LONG).show() );
                    }
                }).start();

                break;
            case R.id.checkInvite2_Cancel:
                uname.setText(" ");
                pwd.setText(" ");
                matnr.setText(" ");

                break;

        }
    }

}