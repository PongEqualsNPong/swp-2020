package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.ServerJoin;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    Button buttonChangePassword;
    Button buttonViewProfile;
    Button buttonJoinServer;
    Button buttonCreateProject;
    EditText fieldChangePassword;

    String username, newPassword, oldPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        username = getIntent().getStringExtra("username");
        oldPassword = getIntent().getStringExtra("password");

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("Welcome back " + username);

        buttonChangePassword = (Button) findViewById(R.id.button_change);
        fieldChangePassword = (EditText) findViewById(R.id.field_change);
        //TODO: new OnClickListener for buttonViewProfile
        buttonViewProfile = (Button) findViewById(R.id.button_viewprofile);
        buttonJoinServer = (Button) findViewById(R.id.button_joinserver);

        buttonChangePassword.setOnClickListener(this);
        buttonViewProfile.setOnClickListener(this);
        buttonJoinServer.setOnClickListener(this);


        buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);
        buttonCreateProject.setOnClickListener(this);




    }

    public void startLogin() {
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("username", username);
        intent.putExtra("password", newPassword);
        startActivity(intent);
    }

//    @Override
    public void onClick(View view) {
        //toast checking pw field is not null
        switch (view.getId()) {

            case R.id.button_change:
                if (fieldChangePassword.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Password must not be empty", Toast.LENGTH_SHORT).show();
                } else {
                    newPassword = fieldChangePassword.getText().toString();
                    startLogin();
                }
                break;
            case R.id.button_viewprofile:
                Toast.makeText(this, "SHOULD BE DIFFERENT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_joinserver:
                //Toast.makeText(this, "JOIN BUTTON", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ServerJoin.class);
                // bp
                startActivity(intent);

            case R.id.buttonCreateProject:
                startActivityCreateProject(view);



            }
    }

//     on click of toolbar item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent intent = new Intent(this, Login.class);
                intent.putExtra("username", username);
                intent.putExtra("password", oldPassword);
                startActivity(intent);

        }
        return true;
    }

    // create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // start Activities
    public void startActivityCreateProject(View view){
        Intent intent = new Intent(this, CreateProject.class);
        startActivity(intent);
    }

}
