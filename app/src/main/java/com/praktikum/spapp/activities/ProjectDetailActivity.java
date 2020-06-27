package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditProjectForm;
import com.praktikum.spapp.models.Project;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class ProjectDetailActivity extends AppCompatActivity {

    Bundle bundle = new Bundle();
    private TextView projectNr, projectName, projectDescription, projectStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Project project = (Project) getIntent().getSerializableExtra("project");
        bundle.putSerializable("project", project);


        BottomNavigationView botNav = findViewById(R.id.bottom_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);

        Fragment beginActivity = new FragmentProjectOverview();
        beginActivity.setArguments(bundle);

        //TODO: prevents this from crashing
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    beginActivity).commit();
        }

        //TODO: Prevent this from crashing
        //projectNr = (TextView) findViewById(R.id.projectNr);
        //projectNr.setText(project.getId());

        projectName = (TextView) findViewById(R.id.et_projectName);
        projectName.setText(project.getName());

        projectDescription = (TextView) findViewById(R.id.et_projectDescription);
        projectDescription.setText(project.getDescription());


        //Edit button
        Button buttonProjectEdit = findViewById(R.id.button_edit_project_and_cancel);
        Button buttonProjectSave = findViewById(R.id.button_edit_project_save);

        //Button buttonProjectDelete = findViewById(R.id.button_project_delete2);

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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = menuItem -> {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {

            case R.id.nav_project_overview:
                selectedFragment = new FragmentProjectOverview();
                selectedFragment.setArguments(bundle);
                break;
            case R.id.nav_project_comments:
                selectedFragment = new FragmentProjectComments();
                selectedFragment.setArguments(bundle);
                break;
            case R.id.nav_project_appointments:
                selectedFragment = new FragmentProjectAppointments();
                selectedFragment.setArguments(bundle);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };
}
