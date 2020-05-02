package com.praktikum.spapp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Refused extends AppCompatActivity implements View.OnClickListener {



    String returnCode;
    Button returnButton;
    EditText returnField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refused);

        returnCode = "322";
        returnButton = (Button) findViewById(R.id.button_try_again);
        returnField = (EditText) findViewById(R.id.field_change);

        returnButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
