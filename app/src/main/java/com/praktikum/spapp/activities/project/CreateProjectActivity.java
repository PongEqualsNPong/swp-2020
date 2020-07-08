package com.praktikum.spapp.activities.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.enums.ProjectStatus;
import com.praktikum.spapp.models.enums.ProjectType;

public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener {

    // UI Elements
    EditText textFieldEnterProjectName;
    EditText textFieldEnterProjectDescription;
    EditText enterProjectType, enterProjectStatus;
    EditText enterProjectWorker, enterProjectCoordinator;
    Button buttonCreate, buttonCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);


        // assign UI Elements
        textFieldEnterProjectName = findViewById(R.id.createFullProject_inputProjectName);
        textFieldEnterProjectDescription = findViewById(R.id.createFullProject_inputProjectDescription);
        enterProjectType = findViewById(R.id.createFullProject_inputProjectType);
        enterProjectStatus = findViewById(R.id.createFullProject_inputProjectStatus);
        enterProjectCoordinator = findViewById(R.id.createFullProject_inputProjectCoordinator);
        enterProjectWorker = findViewById(R.id.createFullProject_inputProjectWorker);

        buttonCancel = findViewById(R.id.createFullProject_buttonCancel);
        buttonCreate = findViewById(R.id.createFullProject_buttonCreate);

        buttonCreate.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    //    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View view) {

        Project project = new Project();

        switch (view.getId()) {
            case R.id.createFullProject_buttonCreate:
                project.setName(textFieldEnterProjectName.getText().toString());
                project.setDescription(textFieldEnterProjectDescription.getText().toString());
                project.setType(ProjectType.valueOf(enterProjectType.getText().toString()));
                project.setProjectStatus(ProjectStatus.valueOf(enterProjectStatus.getText().toString()));
                //todo
                //enter the handler and coordinator array to project
                ProjectService projectService = new ProjectService();

                new Thread(() -> {
                    try {
                        String resultString = projectService.projectCreateFull(project);
                        System.out.println(resultString);
                        if(Utils.isSuccess(resultString)){
                            runOnUiThread(() -> Snackbar.make(view, "Project Created, now get out", Snackbar.LENGTH_LONG).show());
                        } else {
                            runOnUiThread(() ->  Snackbar.make(view, "something happened", Snackbar.LENGTH_LONG).show());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(() ->  Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show());
                    }

                }).start();
                break;

            case R.id.createFullProject_buttonCancel:
                textFieldEnterProjectDescription.setText("");
                textFieldEnterProjectName.setText("");
                enterProjectWorker.setText("");
                enterProjectCoordinator.setText("");
                enterProjectStatus.setText("");
                enterProjectType.setText("");

                break;
        }


    }


}