package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.Random;

public class ServerJoin extends AppCompatActivity implements View.OnClickListener {

    Button join_button;
    //TODO: STOPS CRASHING
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serverjoin);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("JOIN");

        join_button = (Button) findViewById(R.id.join_button);
        join_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            Toast.makeText(this,"JOIN BUTTON",Toast.LENGTH_SHORT).show();
    }
}
