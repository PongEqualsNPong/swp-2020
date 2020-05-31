package com.praktikum.spapp.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.praktikum.spapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // variable from intend
    String password, username;
    // variables given by input xml elemnt
    String givenName, givenPassword;
    // xml elements
    EditText loginName, loginPassword;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("Login");

        //
        Intent intent = getIntent();
        password = intent.getStringExtra("password");
        username = intent.getStringExtra("username");

        // assign xml to variables
        loginName = (EditText) findViewById(R.id.userName);
        loginPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    // start refuse activity
    // open the Refused View page.
    public void startRefused(View view){
        Intent intent = new Intent(this, RefusedActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }
    // start welcome activity
    public void startWelcome(View view){
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        // bp
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {


        //assign intend extras to variables
        givenName = loginName.getText().toString();
        givenPassword = loginPassword.getText().toString();

        // compare
        if(givenName.equals(username) && givenPassword.equals(password)){
            startWelcome(view);
        } else {
            startRefused(view);
        }
    }
}