package com.praktikum.spapp.activities.project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.service.internal.ProjectServiceImpl;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterProject;

import java.io.IOException;
import java.util.ArrayList;

public class OpenAllProjectsActivity extends AppCompatActivity {
    ArrayList<Project> projectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_all_projects);

        new Thread(() -> {
            try {
                ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
                this.projectArrayList = projectServiceImpl.fetchAllProjects();

                runOnUiThread(() -> {try {
                    initRecyclerView();
                } catch (Exception e) {
                    e.printStackTrace();
                }});


            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();

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
