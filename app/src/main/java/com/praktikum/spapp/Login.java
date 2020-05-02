package com.praktikum.spapp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity implements View.OnClickListener {

    String password, username;
    String givenName, givenPassword;

    EditText loginName, loginPassword;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Intent intent = getIntent();
        password = intent.getStringExtra("password");
        username = intent.getStringExtra("username");

        loginName = (EditText) findViewById(R.id.userName);
        loginPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    public void startRefused(View view){
        Intent intent = new Intent(this, Refused.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void startWelcome(View view){
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("username", username);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        givenName = loginName.getText().toString();
        givenPassword = loginPassword.getText().toString();


        if(givenName.equals(username) && givenPassword.equals(password)){
            startWelcome(view);
        } else {
            startRefused(view);
        }
    }
}
