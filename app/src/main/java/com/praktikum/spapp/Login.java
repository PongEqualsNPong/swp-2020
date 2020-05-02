package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText loginName, loginPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginName = (EditText) findViewById(R.id.userName);
        loginPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(loginName.toString(),loginPassword.toString());
            }
        });
    }

    public void validate(String loginName, String loginPassword ) {
        if(loginName == getIntent().getStringExtra("USERNAME") && loginPassword == getIntent().getStringExtra("PASSWORD"));
        startActivity(new Intent(this, Refused.class));

    }

    @Override
    public void onClick(View v) {

    }
}
