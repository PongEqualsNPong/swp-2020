package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;

public class DetailProjectActivity extends AppCompatActivity{
    private TextView projectNr, projectName, projectDescription, projectStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Project project = (Project) getIntent().getSerializableExtra("project");

        projectNr = (TextView) findViewById(R.id.projectNr);
        projectNr.setText(project.getId());

        projectName = (TextView) findViewById(R.id.projectName);
        projectNr.setText(project.getName());

        projectDescription = (TextView) findViewById(R.id.projectDescription);
        projectDescription.setText(project.getDescription());

        projectStatus = (TextView) findViewById(R.id.vorname);
        projectStatus.setText(project.getStatus().toString());
    }

}
