package com.praktikum.spapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Refused extends AppCompatActivity {

    Button comeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refused);

        //button to comeback
        comeBackLogin();
    }

    public void comeBackLogin() {
        comeBack = (Button) findViewById(R.id.button);

        comeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    public void openLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
