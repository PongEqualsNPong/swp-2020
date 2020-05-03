package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    Button buttonChangePassword;
    EditText fieldChangePassword;

    String username, newPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        buttonChangePassword = (Button) findViewById(R.id.button_change);
        fieldChangePassword = (EditText) findViewById(R.id.field_change);

        username = getIntent().getStringExtra("username");

        buttonChangePassword.setOnClickListener(this);


    }
    public void startLogin(View view){

        Intent intent = new Intent(this, Login.class);
        intent.putExtra("username", username);
        intent.putExtra("password", newPassword);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        newPassword = fieldChangePassword.getText().toString();
        startLogin(view);
    }
}
