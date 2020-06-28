package com.praktikum.spapp.activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditProjectForm;
import com.praktikum.spapp.models.Project;

import java.util.concurrent.atomic.AtomicBoolean;

public class FragmentProjectOverview extends Fragment implements View.OnClickListener {
    EditText pdTitle;
    EditText pdDescription;
    EditText pdStatus;
    EditText pdType;

    Button editProject;
    Button editSave;

    private String projectName, projectDescription, projectStatus;
    private int projectNr;
    Button buttonProjectEdit, buttonProjectSave;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_overview, container, false);
        Project project = (Project) getArguments().getSerializable("project");
        pdTitle = view.findViewById(R.id.pd_title);
        pdTitle.setText(project.getName());
        pdDescription = view.findViewById(R.id.pd_description);
        pdDescription.setText(project.getDescription());
        pdStatus = view.findViewById(R.id.pd_status);
        pdStatus.setText(project.getProjectStatus().toString());
        pdType = view.findViewById(R.id.pd_type);
        pdType.setText(project.getType().toString());

        projectName = project.getName();
        projectDescription = project.getDescription();
        projectNr = project.getId();

        //Edit button
        buttonProjectEdit = view.findViewById(R.id.button_edit_project_and_cancel);
        buttonProjectSave = view.findViewById(R.id.button_edit_project_save);

        //buttonProjectSave.setOnClickListener(this);
        buttonProjectEdit.setOnClickListener(this);

        return view;


    }
    AtomicBoolean editMode = new AtomicBoolean(false);

    @Override
    public void onClick(View view) {
        if (!editMode.get()) {
            editMode.set(true);

            buttonProjectEdit.setText("Cancel");
            buttonProjectSave.setVisibility(View.VISIBLE);

            EditProjectForm editProjectForm = new EditProjectForm();

            editProjectForm.setName(pdTitle.getText().toString());
            editProjectForm.setDescription(pdDescription.getText().toString());

            new Thread(() -> {
                try {
                    String responseString = new ProjectService().updateProject(editProjectForm, projectNr);
                    if (Utils.isSuccess(responseString)) {
                        getActivity().runOnUiThread(() -> {

                            editMode.set(false);
                            buttonProjectEdit.setText("Edit");
                            buttonProjectSave.setVisibility(View.GONE);

                            editProjectForm.setName(projectName);
                            editProjectForm.setDescription(projectDescription);
                            Snackbar.make(view, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                        });
                    } else {
                        getActivity().runOnUiThread(() -> {
                            Snackbar.make(view, Utils.parseForJsonObject(responseString, "Error"), Snackbar.LENGTH_LONG).show();
                        });

                    }
                } catch (Exception e) {
                    getActivity().runOnUiThread(() -> {
                        Snackbar.make(view, "Whoops, something went wrong.", Snackbar.LENGTH_LONG).show();
                    });
                }
            }).start();
        } else {
            pdTitle.setText(projectName);
            pdTitle.setText(projectDescription);

            editMode.set(false);

            buttonProjectEdit.setText("Edit");
            buttonProjectSave.setVisibility(View.GONE);
        }
    }
}
