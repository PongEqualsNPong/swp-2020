package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditProjectForm;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.Project;

import java.util.concurrent.atomic.AtomicBoolean;

public class DetailProjectActivity extends AppCompatActivity{
    private TextView projectNr;
    private TextView projectName;
    private TextView projectDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Project project = (Project) getIntent().getSerializableExtra("project");

        //TODO: Prevent this from crashing
        //projectNr = (TextView) findViewById(R.id.projectNr);
        //projectNr.setText(project.getId());

        projectName = (TextView) findViewById(R.id.et_projectName);
        projectName.setText(project.getName());

        projectDescription = (TextView) findViewById(R.id.et_projectDescription);
        projectDescription.setText(project.getDescription());


        //Edit button
        Button buttonProjectEdit = findViewById(R.id.button_edit_project_and_cancel2);
        Button buttonProjectSave = findViewById(R.id.button_edit_project_save2);

        Button buttonProjectDelete = findViewById(R.id.button_project_delete2);

        AtomicBoolean editMode = new AtomicBoolean(false);

        buttonProjectEdit.setOnClickListener((
                View view) -> {

            if (!editMode.get()) {
                editMode.set(true);
                projectName.setEnabled(true);
                projectDescription.setEnabled(true);

                buttonProjectEdit.setText("Cancel");
                buttonProjectSave.setVisibility(View.VISIBLE);


                buttonProjectSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditProjectForm editProjectForm = new EditProjectForm();
                        editProjectForm.setName(projectName.getText().toString());
                        editProjectForm.setDescription(projectDescription.getText().toString());

                        new Thread(() -> {
                            try {
                                String responseString = new ProjectService().updateProject(editProjectForm, projectNr.getId());
                                if (Utils.isSuccess(responseString)) {
                                    runOnUiThread(() -> {

                                        editMode.set(false);
                                        projectName.setEnabled(false);
                                        projectDescription.setEnabled(false);
                                        buttonProjectEdit.setText("Edit");
                                        buttonProjectSave.setVisibility(View.GONE);

                                        projectName.setText(editProjectForm.getName());
                                        projectDescription.setText(editProjectForm.getDescription());
                                        Snackbar.make(view, "Con fuckign gratys, your changes were saved.", Snackbar.LENGTH_LONG).show();
                                    });
                                } else {
                                    runOnUiThread(() -> {
                                        Snackbar.make(view, Utils.parseForJsonObject(responseString, "Error"), Snackbar.LENGTH_LONG).show();
                                    });

                                }
                            } catch (Exception e) {
                                runOnUiThread(() -> {
                                    Snackbar.make(view, "Whoops, something went wrong.", Snackbar.LENGTH_LONG).show();
                                });
                            }
                        }).start();
                    }
                });
            } else {
                projectName.setText(project.getName());
                projectDescription.setText(project.getDescription());

                editMode.set(false);
                projectName.setEnabled(false);
                projectDescription.setEnabled(false);
                buttonProjectEdit.setText("Edit");
                buttonProjectSave.setVisibility(View.GONE);
            }
        });
    }

}
