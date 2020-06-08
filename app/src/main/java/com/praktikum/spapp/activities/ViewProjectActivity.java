package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterProject;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterUser;

import java.io.IOException;
import java.util.ArrayList;

public class ViewProjectActivity extends AppCompatActivity {


    ArrayList<Project> projectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        new Thread(() -> {
            try {
                ProjectService projectService = new ProjectService();
                //this.projectArrayList = projectService.fetchAllProjects();
                this.projectArrayList = projectService.fetchProjectsDetail();


                runOnUiThread(() -> {
                    try {
                        initRecyclerView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void initRecyclerView() throws IOException {
        RecyclerView recyclerView = findViewById(R.id.project_recycler_view);
        RecyclerViewAdapterProject adapter = new RecyclerViewAdapterProject(projectArrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
