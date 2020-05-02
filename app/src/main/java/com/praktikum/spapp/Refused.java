package com.praktikum.spapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Refused extends AppCompatActivity implements View.OnClickListener {


    // constant that needs to be put in
    final String returnCode = "322";
    String password, username;
    Button returnButton;
    EditText returnField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refused);

        returnButton = (Button) findViewById(R.id.button_try_again);
        returnField = (EditText) findViewById(R.id.field_enter_code);

        password = getIntent().getStringExtra("password");
        username = getIntent().getStringExtra("username");

        returnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String field = returnField.getText().toString();

        if(field.equals(returnCode)) {
            startLogin(view);
        } else {
            startRegister(view);
        }


    }
    public void startLogin(View view){
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void startRegister(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
