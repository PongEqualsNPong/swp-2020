package com.praktikum.spapp.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import com.praktikum.spapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userName, password;
    Button sign_up;

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        sign_up = (Button) findViewById(R.id.sign_up);

        //hardcoding toolbar title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register new login");
        sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startLogin(view);
    }

    public void startLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle extras = new Bundle();
        String sendName = userName.getText().toString();
        String sendPassword = password.getText().toString();
        extras.putString("username", sendName);
        extras.putString("password", sendPassword);

        intent.putExtras(extras);

        startActivity(intent);
    }
}



