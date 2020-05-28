package com.praktikum.spapp.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.dto.Project;
import org.json.JSONException;

import java.io.IOException;

public class CreateProject extends AppCompatActivity implements View.OnClickListener {

    // UI Elements
    EditText textFieldEnterProjectName;
    EditText textFieldEnterProjectDescription;
    Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        // assign UI Elements
        textFieldEnterProjectName = (EditText) findViewById(R.id.enterProjectName);
        textFieldEnterProjectDescription = (EditText) findViewById(R.id.enterProjectDescription);
        buttonCreate = (Button) findViewById(R.id.buttonCreateProject);

        buttonCreate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        String


        final Project project = new Project(){};
        project.setName(textFieldEnterProjectName.getText().toString());
        project.setName(textFieldEnterProjectDescription.getText().toString());

        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                ProjectService projectService = new ProjectService();
                try {
                    projectService.projectCreate(project);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
