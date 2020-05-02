package com.praktikum.spapp;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText registerName, registerPassword;
    Button sign_up;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registerName = (EditText) findViewById(R.id.userName);
        registerPassword = (EditText) findViewById(R.id.password);
        sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up:

                Intent intent = new Intent(this, Login.class);
                intent.putExtra( "USERNAME",registerName.toString());
                intent.putExtra( "PASSWORD",registerPassword.toString());

                startActivity(intent);
                break;
//                startActivity(new Intent(this, Refused.class));

        }
    }
}




