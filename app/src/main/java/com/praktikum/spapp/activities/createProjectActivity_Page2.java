package com.praktikum.spapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.enums.ProjectType;

import java.util.ArrayList;
import java.util.List;


public class createProjectActivity_Page2 extends AppCompatActivity implements View.OnClickListener {
    String project_name, project_description, project_type_string, project_status;
    EditText projectCoordinator, projectWorker;
    String givenPC, givenPW;
    Button confirm, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project2);


        //initialise variable passed from createProject 1

        Intent intent = getIntent();
        project_name = intent.getStringExtra("project_name");
        project_description = intent.getStringExtra("project_description");
        project_type_string = intent.getStringExtra("project_type_string");
        project_status = intent.getStringExtra("project_status");

        //assign xml elems
        projectCoordinator = findViewById(R.id.inputProjectCoordinator);
        projectWorker = findViewById(R.id.inputProjectWorker);
        confirm = findViewById(R.id.createProject2_button_confirm);
        cancel = findViewById(R.id.createProject2_button_cancel);

    }

    public void onClick(View view) {
        ArrayList<User> handlerList = new ArrayList<>();
        givenPC = projectCoordinator.getText().toString();
        givenPW = projectWorker.getText().toString();

        //ask Tutor about this, not sure where to create UserList base on the inputText
        //prerequisites: already have a serializable User class, not sure how to pass that into a ArrayList here


        Project project = new Project();
        project.setType(ProjectType.valueOf(project_type_string));
        project.setDescription(project_description);
        project.setHandler(handlerList);

        switch (view.getId()) {
            case R.id.createProject2_button_confirm:

                //TODO
                //create a JSON GET request, with full body and send to server
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