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
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "Welcome Activity";

    ArrayList<User> userArrayList;

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

        // custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hardcoded rn
        getSupportActionBar().setTitle("Welcome back ");

        buttonViewProfile = (Button) findViewById(R.id.button_viewprofile);
        buttonViewProfile.setOnClickListener(this);



        buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);
        buttonCreateProject.setOnClickListener(this);

        buttonInviteUser = (Button) findViewById(R.id.button_invite);
        buttonInviteUser.setOnClickListener(this);

        buttonOpenProject = (Button) findViewById(R.id.button_openproject);
        buttonOpenProject.setOnClickListener(this);

        buttonViewProjects = (Button) findViewById(R.id.button_viewprojects);
        buttonViewProjects.setOnClickListener(this);
    }

    //    @Override
    public void onClick(View view) {
        //toast checking pw field is not null
        switch (view.getId()) {

            case R.id.button_viewprofile:
                startViewProfile();
                break;
            case R.id.buttonCreateProject:
                startActivityCreateProject(view);
                break;
            case R.id.button_invite:
                startActivityInvite();
                break;
            case R.id.button_openproject:
                startActivityOpenProject();
                break;
            case R.id.button_viewprojects:
                startActivityOpenMyProjects();
        }
    }



    //     on click of toolbar item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent intent = new Intent(this, LoginActivity.class);
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
    public void startActivityCreateProject(View view) {
        Intent intent = new Intent(this, CreateProjectActivity.class);
        startActivity(intent);
    }

    private void startViewProfile() {
        startActivity( new Intent(this, ShowFetchedUsersActivity.class));
    }

    private void startActivityCheckForInvite() {
        Intent intent = new Intent(this, CheckForInviteActivity.class);
        startActivity(intent);
    }

    private void startActivityOpenProject() {
        Intent intent = new Intent(this, OpenAllProjectsActivity.class);
        startActivity(intent);
    }

    private void startActivityInvite() {
        Intent intent = new Intent(this, InviteActivity.class);
        startActivity(intent);
    }
    private void startActivityOpenMyProjects() {
        startActivity(new Intent(this, OpenMyProjectsActivity.class));
    }

}

