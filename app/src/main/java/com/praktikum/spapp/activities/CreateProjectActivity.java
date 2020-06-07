package com.praktikum.spapp.activities;

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
import org.json.JSONException;

import java.io.IOException;

public class CreateProjectActivity extends AppCompatActivity implements View.OnClickListener {

    // UI Elements
    EditText textFieldEnterProjectName;
    EditText textFieldEnterProjectDescription;
    Button buttonCreate;
    Snackbar snackbar;


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

    //    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {


        Project project = new Project() {
        };
        project.setName(textFieldEnterProjectName.getText().toString());
        project.setName(textFieldEnterProjectDescription.getText().toString());

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
}
