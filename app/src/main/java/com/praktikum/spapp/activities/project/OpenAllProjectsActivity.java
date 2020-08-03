package com.praktikum.spapp.activities.project;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Session;
import com.praktikum.spapp.service.ProjectService;
import com.praktikum.spapp.service.internal.ProjectServiceImpl;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterProject;

import java.io.IOException;
import java.util.ArrayList;

public class OpenAllProjectsActivity extends AppCompatActivity {
    ArrayList<Project> projectArrayList;
    ProjectService service = new ProjectServiceImpl(SessionManager.getSession());
    CardView noProjectsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_all_projects);
        noProjectsMessage = findViewById(R.id.cardViewNoProjects);

        new Thread(() -> {
            try {
                this.projectArrayList = service.fetchAllProjects();
                if (projectArrayList.isEmpty()) {
                    noProjectsMessage.setVisibility(View.VISIBLE);
                } else {
                    runOnUiThread(this::initRecyclerView);
                }
            } catch (ResponseException e) {
                runOnUiThread(Snackbar.make(findViewById(R.id.activity_open_all_projects), e.getMessage() + " Please return to the previous page.", Snackbar.LENGTH_LONG)::show);
            }
        }).start();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.project_recycler_view);
        RecyclerViewAdapterProject adapter = new RecyclerViewAdapterProject(projectArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
