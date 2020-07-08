package com.praktikum.spapp.activities.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;

import java.util.ArrayList;


public class CreateProjectActivity2 extends AppCompatActivity implements View.OnClickListener {

    EditText projectName, projectDesc;
    String givenPN, givenPD;
    Button confirm, cancel, createFullProject;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project2);



        //assign xml elems
        projectName = findViewById(R.id.inputProjectName);
        projectDesc = findViewById(R.id.inputProjectDescription);
        confirm = findViewById(R.id.createProject2_button_confirm);
        cancel = findViewById(R.id.createProject2_button_cancel);
        createFullProject = findViewById(R.id.createProject_buttonCreateExtendedProject);


        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        createFullProject.setOnClickListener(this);
    }

    public void onClick(View view) {
        ArrayList<User> handlerList = new ArrayList<>();
        givenPN = projectName.getText().toString();
        givenPD = projectDesc.getText().toString();



        Project project = new Project();
        project.setName(givenPN);
        project.setDescription(givenPD);


        switch (view.getId()) {
            case R.id.createProject2_button_confirm:

                ProjectService projectService = new ProjectService();

                new Thread(() -> {
                    try {
                        String resultString = projectService.projectCreate(project) ;

                        //  projectService.projectCreate(project);
                        System.out.println(resultString);
                        if(Utils.isSuccess(resultString)) {
                            runOnUiThread(() -> Snackbar.make(view, "get the fuck out", Snackbar.LENGTH_LONG).show());

                        }
                        else {
                            runOnUiThread(() ->  Snackbar.make(view, "something happened", Snackbar.LENGTH_LONG).show());
                        }
                    } catch (Exception e) {
                        runOnUiThread(() ->Snackbar.make(view, e.getMessage().toString(), Snackbar.LENGTH_LONG).show() );

                        e.printStackTrace();
                    }
                }).start();


                break;
            case R.id.createProject2_button_cancel:
                //return to the createProject window
               projectName.setText("");
               projectDesc.setText("");
                break;
            case R.id.createProject_buttonCreateExtendedProject:
                runOnUiThread(() -> {
                    startActivity(new Intent(this, CreateProjectActivity.class));
                });
        }

    }
}