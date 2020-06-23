package com.praktikum.spapp.activities.general;

import android.app.Dialog;
import android.content.Intent;
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
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.AuthenticationService;
import com.praktikum.spapp.activities.user.CheckForInviteActivity;
import com.praktikum.spapp.common.Utils;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    // xml elements
    EditText etLoginName;
    EditText etLoginPassword;
    Button butLogin;
    Dialog myDialog;
    TextView tvClickWithInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        myDialog = new Dialog(this);

        // assign xml to variables
        etLoginName = findViewById(R.id.userName);
        etLoginPassword = findViewById(R.id.password);
        butLogin = findViewById(R.id.login);
        tvClickWithInvite = findViewById(R.id.inviteClick);


        butLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    try {
//                        if(etLoginName.getText().toString().contains("@"))
                        //save static token
                        String responseBody = new AuthenticationService().loginOnServer(etLoginName.getText().toString(), etLoginPassword.getText().toString());
                        //Activity will be shown next Intent will be changed
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        if (AuthenticationService.getToken()!=null) {

                            runOnUiThread(() -> {
                                //Intent will be started
                                startActivity(intent);
                            });
                        } else {
                            runOnUiThread(() -> {
                                Snackbar.make(view, Utils.jsonGetErrorMessage(responseBody) ,Snackbar.LENGTH_SHORT).show();
                            });
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
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

        txtclose = myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
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



