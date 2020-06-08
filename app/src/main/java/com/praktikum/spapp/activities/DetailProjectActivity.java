package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;

import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;

import static com.praktikum.spapp.Service.Service.JSON;

public class DetailProjectActivity extends AppCompatActivity{
    //string from intent
    String userName, userPassword, studentNumber, firstName, lastName, invitationLink;
    String projectNr, projectName;
    //string from xml elems
    String givenName, givenPWD, givenSN, givenMJ, givenPO;
    //XML elems
    EditText uname, pwd, matnr, major, po;
    Button buttonConfirm, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

    }

}
