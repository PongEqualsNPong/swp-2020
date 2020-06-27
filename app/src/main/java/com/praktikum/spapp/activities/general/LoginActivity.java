package com.praktikum.spapp.activities.general;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        // assign xml to variables
        etLoginName = findViewById(R.id.userName);
        etLoginPassword = findViewById(R.id.password);
        butLogin = findViewById(R.id.login);
        tvClickWithInvite = findViewById(R.id.inviteClick);


        butLogin.setOnClickListener((View view) -> {
                new Thread(() -> {
                    try {
//                        if(etLoginName.getText().toString().contains("@"))
                        //save static token
                        String responseBody = new AuthenticationService().loginOnServer(etLoginName.getText().toString(), etLoginPassword.getText().toString());
                        //Activity will be shown next Intent will be changed
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        if (AuthenticationService.getToken() != null) {

                            runOnUiThread(() -> {
                                //Intent will be started
                                startActivity(intent);
                            });
                        } else {
                            runOnUiThread(() -> {
                                Snackbar.make(view, Utils.parseForJsonObject(responseBody,"Error"), Snackbar.LENGTH_SHORT).show();
                            });
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

            });

        tvClickWithInvite.setOnClickListener((View view) -> {
            startActivity(new Intent(LoginActivity.this, CheckForInviteActivity.class));
        });
    }
}



