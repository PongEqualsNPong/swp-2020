package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;

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
            case R.id.nav_project_participants:
                selectedFragment = new FragmentProjectParticipants();
                selectedFragment.setArguments(bundle);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };
}
