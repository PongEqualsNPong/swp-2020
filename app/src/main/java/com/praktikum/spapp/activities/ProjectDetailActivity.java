package com.praktikum.spapp.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.service.internal.ProjectServiceImpl;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;

import static java.lang.Integer.parseInt;

public class ProjectDetailActivity extends AppCompatActivity {

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Project project = (Project) getIntent().getSerializableExtra("project");
        bundle.putSerializable("project", project);

        BottomNavigationView botNav = findViewById(R.id.bottom_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);

        Fragment beginActivity = new FragmentProjectOverview();
        beginActivity.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    beginActivity).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = menuItem -> {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {

            case R.id.nav_project_overview:
                selectedFragment = new FragmentProjectOverview();
                selectedFragment.setArguments(bundle);
                break;
            case R.id.nav_project_comments:
                selectedFragment = new FragmentProjectComments();
                selectedFragment.setArguments(bundle);
                break;
            case R.id.nav_project_appointments:
                selectedFragment = new FragmentProjectAppointments();
                selectedFragment.setArguments(bundle);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };

}
