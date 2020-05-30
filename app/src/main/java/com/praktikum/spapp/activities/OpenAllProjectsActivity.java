package com.praktikum.spapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class OpenAllProjectsActivity extends AppCompatActivity {

    ArrayList<Project> projects = new ArrayList<>();
    Project project1 = new Project("AYAYA", "only for weebs");
    Project project2 = new Project("Kreygasm", "only for gachis");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_all_projects);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);
        projects.add(project1);
        projects.add(project2);



        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.project_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(projects, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
