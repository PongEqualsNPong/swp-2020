package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    EditText loginName, loginPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginName = (EditText) findViewById(R.id.userName);
        loginPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        //when user submit
        onSubmit();
    }

    public boolean validateCredentials() {
         loginName = (EditText) findViewById(R.id.userName);
         loginPassword = (EditText) findViewById(R.id.password);

        if(loginName.getText().toString().equals("admin")  && loginPassword.getText().toString().equals("admin") )
            return true;

        else return false;

    }

    public void onSubmit() {
        login = (Button) findViewById(R.id.login);

        if(validateCredentials()==true) {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChangePWD();
                }
            });
        }
        else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openRefused();
                }
            });
        }
    }

    public void openChangePWD() {
        Intent changePWD = new Intent(this, changePWD.class);
        startActivity(changePWD);
    }

    public void openRefused() {
        Intent  refused = new Intent(this, Refused.class);
        startActivity(refused);
    }


}
