package com.praktikum.spapp.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Token;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // variable from intend
    String password, username;
    // variables given by input xml elemnt
    String givenName, givenPassword;
    // xml elements
    EditText loginName, loginPassword;
    Button loginButton;
    Dialog myDialog;
    static Token token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("Login");
        myDialog = new Dialog(this);
        Intent intent = getIntent();
        password = intent.getStringExtra("password");
        username = intent.getStringExtra("username");

        // assign xml to variables
        loginName = (EditText) findViewById(R.id.userName);
        loginPassword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        //assign intend extras to variables
        givenName = loginName.getText().toString();
        givenPassword = loginPassword.getText().toString();
        AtomicBoolean credentinals = new AtomicBoolean(false);
        new Thread(() -> {
            UserService userService = new UserService();
            try {
                token = (Token) userService.loginOnServer(givenName, givenPassword);
                //Activity will be shown next Intent will be changed
                Intent intent = new Intent(this, WelcomeActivity.class);
                //Map
                intent.putExtra("token", token);
                //System.out.print(userArrayList.getSuccess());
                //TODO
                if(token.getSuccess().equals("1")) {
                    System.out.println(token.getSuccess());
                    runOnUiThread(() -> {
                        //Intent will be started
                        startActivity(intent);
                    });
                 }else{
                    runOnUiThread(new Runnable() {
                        public void run() {
                            ShowPopup();
                        }
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.activity_static_pop_up);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}


