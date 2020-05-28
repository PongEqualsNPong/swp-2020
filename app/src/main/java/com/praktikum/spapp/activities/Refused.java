package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.praktikum.spapp.R;

import java.util.Random;

public class Refused extends AppCompatActivity implements View.OnClickListener {


    // constant that needs to be put in
    final int returnCode = 1000 + new Random().nextInt(9000);
    TextView codeNumbers;
    String password, username;
    Button returnButton;
    EditText returnField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refused);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("Refused Login");

        returnButton = (Button) findViewById(R.id.button_try_again);
        returnField = (EditText) findViewById(R.id.field_enter_code);
        codeNumbers = (TextView) findViewById(R.id.code_numbers);
        codeNumbers.setText(Integer.toString(returnCode));

        password = getIntent().getStringExtra("password");
        username = getIntent().getStringExtra("username");

        returnButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String field = returnField.getText().toString();
        if(field.equals(Integer.toString(returnCode))) {
            startLogin(view);
            Toast.makeText(this,"Valid security code",Toast.LENGTH_SHORT).show();
        } else {
//            temporary disable this as you should not advance if code is incorrect
            startRegister(view);
//            Toast.makeText(this,"Invalid security code",Toast.LENGTH_SHORT).show();
        }


    }
    public void startLogin(View view){
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void startRegister(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
