package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;

import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.RequestBody;

import static com.praktikum.spapp.Service.Service.JSON;

public class DetailProjectActivity extends AppCompatActivity{
    private TextView projectNr, projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Project project = (Project) getIntent().getSerializableExtra("user");

        projectNr = (TextView) findViewById(R.id.projectNr);
        projectNr.setText(project.getId());

        projectName = (TextView) findViewById(R.id.projectName);
        projectNr.setText(project.getId());
    }

}
