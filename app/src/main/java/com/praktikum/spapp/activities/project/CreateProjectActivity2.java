package com.praktikum.spapp.activities.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.enums.ProjectType;

import java.util.ArrayList;


public class CreateProjectActivity2 extends AppCompatActivity implements View.OnClickListener {

    EditText projectName, projectDesc;
    String givenPN, givenPD;
    Button confirm, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project2);






        //assign xml elems
        projectName = findViewById(R.id.inputProjectCoordinator);
        projectDesc = findViewById(R.id.inputProjectWorker);
        confirm = findViewById(R.id.createProject2_button_confirm);
        cancel = findViewById(R.id.createProject2_button_cancel);

    }

    public void onClick(View view) {
        ArrayList<User> handlerList = new ArrayList<>();
        givenPN = projectName.getText().toString();
        givenPD = projectDesc.getText().toString();

        //ask Tutor about this, not sure where to create UserList base on the inputText
        //prerequisites: already have a serializable User class, not sure how to pass that into a ArrayList here


        Project project = new Project();
        project.setName(givenPN);
        project.setDescription(givenPD);
//        project.setType(ProjectType.valueOf(project_type_string));
//       project.setDescription(project_description);
//        project.setHandler(handlerList);

        switch (view.getId()) {
            case R.id.createProject2_button_confirm:

                //TODO
                ProjectService projectService = new ProjectService();
                try {
                    projectService.projectCreate(project);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
            case R.id.createProject2_button_cancel:
                //return to the createProject window
                runOnUiThread(() -> {
                    startActivity(new Intent(this, CreateProjectActivity.class));
                });
                break;
        }

    }
}