package com.praktikum.spapp.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Token;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginActivity extends AppCompatActivity {

    // xml elements
    EditText etLoginName;
    EditText etLoginPassword;
    Button butLogin;
    Dialog myDialog;
    TextView tvClickWithInvite;
    static Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        //getSupportActionBar().setTitle("Login");
        myDialog = new Dialog(this);

        // assign xml to variables
        etLoginName = (EditText) findViewById(R.id.userName);
        etLoginPassword = (EditText) findViewById(R.id.password);
        butLogin = (Button) findViewById(R.id.login);
        tvClickWithInvite = (TextView) findViewById(R.id.inviteClick);


        butLogin.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                AtomicBoolean credentinals = new AtomicBoolean(false);
                new Thread(() -> {
                    UserService userService = new UserService();
                    try {
                        token = (Token) userService.loginOnServer(etLoginName.getText().toString(), etLoginPassword.getText().toString());
                        //Activity will be shown next Intent will be changed
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        //Map
                        intent.putExtra("token", token);
                        //System.out.print(userArrayList.getSuccess());
                        //TODO



                        if (token.getSuccess().equals("1")) {

                            System.out.println(token.getSuccess());
                            runOnUiThread(() -> {
                                //Intent will be started
                                startActivity(intent);
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    ShowPopup();
                                }
                            });
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        });

        tvClickWithInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CheckForInviteActivity.class));
            }
        });
    }

    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.activity_static_pop_up);

        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
//        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}


