package com.praktikum.spapp.activities.project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterProject;

import java.io.IOException;
import java.util.ArrayList;

public class OpenMyProjectsActivity extends AppCompatActivity {
    ArrayList<Project> projectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_my_projects);

        new Thread(() -> {
            try {
                ProjectService projectService = new ProjectService();
                this.projectArrayList = projectService.fetchProjectsOnlyFromUser();

                runOnUiThread(() -> {try {
                    initRecyclerView();
                } catch (Exception e) {
                    e.printStackTrace();
                }});


            } catch (IOException e) {
                e.printStackTrace();

//                runOnUiThread(() -> {
//                    ;
//                    startActivity(new Intent(this, WelcomeActivity.class));
//                });
            } catch (Exception e) {
                e.printStackTrace();

//                runOnUiThread(() -> {
//                    ;
//                    startActivity(new Intent(this, WelcomeActivity.class));
//                });
            }

        }).start();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.project_recycler_view);
        RecyclerViewAdapterProject adapter = new RecyclerViewAdapterProject(projectArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
//    public void ShowPopup() {
//        TextView txtclose;
//        Button btnFollow;
//        myDialog.setContentView(R.layout.activity_static_pop_up);
//
//        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("X");
////        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
//        txtclose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }

}