package com.praktikum.spapp.activities.general;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.ProjectDetailActivity;
import com.praktikum.spapp.activities.project.CreateProjectActivity2;
import com.praktikum.spapp.activities.project.OpenAllProjectsActivity;
import com.praktikum.spapp.activities.user.CheckForInviteActivity;
import com.praktikum.spapp.activities.user.InviteActivity;
import com.praktikum.spapp.activities.user.ShowFetchedUsersActivity;
import com.praktikum.spapp.models.User;

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
    Button buttonAppointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        buttonViewProfile = findViewById(R.id.button_viewprofile);
        buttonViewProfile.setOnClickListener(this);

        buttonCreateProject = findViewById(R.id.createFullProject_buttonCreate);
        buttonCreateProject.setOnClickListener(this);

        buttonInviteUser = findViewById(R.id.button_invite);
        buttonInviteUser.setOnClickListener(this);

        buttonOpenProject = findViewById(R.id.button_openproject);
        buttonOpenProject.setOnClickListener(this);

        buttonViewProjects = findViewById(R.id.button_viewprojects);
        buttonViewProjects.setOnClickListener(this);

        CheckForInvite = findViewById(R.id.button_acceptInvite);
        CheckForInvite.setOnClickListener(this);


    }

    //    @Override
    public void onClick(View view) {
        //toast checking pw field is not null
        switch (view.getId()) {

            case R.id.button_viewprofile:
                startViewProfile();
                break;
            case R.id.createFullProject_buttonCreate:
                startActivityCreateProject(view);
                break;
            case R.id.button_invite:
                startActivityInvite();
                break;
            case R.id.button_openproject:
                startActivityOpenProject();
                break;
            case R.id.button_acceptInvite:
                startActivityCheckForInvite();
                break;


            case R.id.button_viewprojects:

                startActivityProjectDetail();

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
        Intent intent = new Intent(this, CreateProjectActivity2.class);
        startActivity(intent);
    }

    private void startViewProfile() {
        startActivity(new Intent(this, ShowFetchedUsersActivity.class));
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

    private void startActivityProjectDetail() {
        startActivity(new Intent(this, ProjectDetailActivity.class));
    }



}

