package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    Button change_pw;
    EditText new_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        change_pw = (Button) findViewById(R.id.button_change);
        new_pw = (EditText) findViewById(R.id.field_change);

        change_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_new_pw(new_pw.toString());
            }
        });
    }

    public void set_new_pw(String newPassword){
        Intent intent =  new Intent(this, Login.class);
        intent.putExtra( "NEWPASSWORD",newPassword.toString());
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

    }
}
