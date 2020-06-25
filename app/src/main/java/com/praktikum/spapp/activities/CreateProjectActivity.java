package com.praktikum.spapp.activities;

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
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.enums.ProjectType;

public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener {

    // UI Elements
    EditText textFieldEnterProjectName;
    EditText textFieldEnterProjectDescription;
    EditText enterProjectType, enterProjectStatus;
    Button buttonCreate;
    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);


        // assign UI Elements
        textFieldEnterProjectName = (EditText) findViewById(R.id.enterProjectName);
        textFieldEnterProjectDescription = (EditText) findViewById(R.id.enterProjectDescription);
        enterProjectType = (EditText) findViewById(R.id.inputProjectType);
        enterProjectStatus = (EditText) findViewById(R.id.inputProjectStatus);
        buttonCreate = (Button) findViewById(R.id.buttonCreateProject);

        buttonCreate.setOnClickListener(this);
    }

    //    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        ProjectType type = ProjectType.valueOf(enterProjectType.getText().toString());

        Project project = new Project() {
        };
        project.setName(textFieldEnterProjectName.getText().toString());
        project.setName(textFieldEnterProjectDescription.getText().toString());
        project.setType(type);
        startCreateProject2(v);
    }


    public void startCreateProject2(View view) {
        Intent intent = new Intent(this, createProjectActivity_Page2.class);
        intent.putExtra("project_name", textFieldEnterProjectName.getText().toString());
        intent.putExtra("project_description", textFieldEnterProjectDescription.getText().toString());
        intent.putExtra("project_type_string", enterProjectType.getText().toString());
        intent.putExtra("project_status", enterProjectStatus.getText().toString());
        startActivity(intent);
    }



/*
        new Thread(() -> {

            try {
                ProjectService projectService = new ProjectService();
                String responseBody = projectService.projectCreate(project);
//                    if(Utils.isSuccess(responseBody)){
                runOnUiThread(() -> {
                    Snackbar.make(findViewById(R.id.coordinator), "ihr penner", Snackbar.LENGTH_SHORT).show();
                });

//                    }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

 */
}
