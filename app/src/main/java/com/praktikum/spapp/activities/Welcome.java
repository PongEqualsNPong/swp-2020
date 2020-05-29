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

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    String username, newPassword, oldPassword;
    // PW CORNER
    Button buttonChangePassword;
    Button buttonJoinServer;
    EditText fieldChangePassword;
    // USER CORNER
    Button buttonViewProfile;
    Button buttonInviteUser;
    Button CheckForInvite;
    //PROJECT CORNER
    Button buttonOpenProject;
    Button buttonCreateProject;
    Button buttonProjectDetails;
    Button buttonViewProjects;

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

        buttonChangePassword.setOnClickListener(this);
        buttonViewProfile.setOnClickListener(this);


        buttonJoinServer = (Button) findViewById(R.id.button_joinserver);
        buttonJoinServer.setOnClickListener(this);

        CheckForInvite = (Button) findViewById(R.id.button_invitecheck);
        CheckForInvite.setOnClickListener(this);

        buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);
        buttonCreateProject.setOnClickListener(this);

        buttonInviteUser = (Button) findViewById(R.id.button_invite);
        buttonInviteUser.setOnClickListener(this);

        buttonOpenProject = (Button) findViewById(R.id.button_openproject);
        buttonOpenProject.setOnClickListener(this);

        buttonProjectDetails = (Button) findViewById(R.id.button_detailproject);
        buttonProjectDetails.setOnClickListener(this);

        buttonViewProjects = (Button) findViewById(R.id.button_viewprojects);
        buttonViewProjects.setOnClickListener(this);
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
                startViewProfile();
                break;
            case R.id.button_joinserver:
                startJoinServer();
                break;
            case R.id.buttonCreateProject:
                startActivityCreateProject(view);
                break;
            case R.id.button_invite:
                startActivityInvite();
                break;
            case R.id.button_invitecheck:
                startActivityCheckForInvite();
                break;
            case R.id.button_openproject:
                startActivityOpenProject();
                break;
            case R.id.button_detailproject:
                startActivityProjectDetails();
                break;
            case R.id.button_viewprojects:
                startActivityViewProject();
                break;
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

    public void startJoinServer(){
        Intent intent = new Intent(this, ServerJoin.class);
        startActivity(intent);
    }

    public void startViewProfile(){
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

    private void startActivityCheckForInvite() {
        Intent intent = new Intent(this, CheckForInvite.class);
        startActivity(intent);
    }

    private void startActivityOpenProject() {
        Intent intent = new Intent(this, OpenProject.class);
        startActivity(intent);
    }

    private void startActivityProjectDetails() {
        Intent intent = new Intent(this, ProjectDetails.class);
        startActivity(intent);
    }
    public void startActivityInvite(){
        Intent intent = new Intent(this, InviteActivity.class);
        startActivity(intent);
    }

    private void startActivityViewProject() {
        Intent intent = new Intent(this, ViewProject.class);
        startActivity(intent);
    }

}