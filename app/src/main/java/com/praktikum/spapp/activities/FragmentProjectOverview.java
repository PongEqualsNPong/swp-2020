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
<<<<<<< Updated upstream
        pdStatus = view.findViewById(R.id.pd_status);
        pdStatus.setText(project.getProjectStatus().toString());
        pdType = view.findViewById(R.id.pd_type);
        pdType.setText(project.getType().toString());
=======

        editAndSaveButton = view.findViewById(R.id.pd_overview_edit_button);
        deleteAndCancelButton = view.findViewById(R.id.pd_overview_save_or_delete_button);


        // SPINNER
        spinnerType = view.findViewById(R.id.pd_overview_spinner_type);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(getContext(),
                R.array.project_type_array, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);
        spinnerType.setEnabled(false);
        if (project.getType() == null) {
            spinnerType.setSelection(0);
        } else {
            spinnerType.setSelection(adapterType.getPosition(project.getType().toString()));
        }

        spinnerStatus = view.findViewById(R.id.pd_overview_spinner_status);
        ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(getContext(),
                R.array.project_status_array, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapterStatus);
        spinnerStatus.setEnabled(false);
        if (project.getProjectStatus() == null) {
            spinnerStatus.setSelection(0);
        } else {
            spinnerStatus.setSelection(adapterStatus.getPosition(project.getStatus().toString()));
        }

        // RECYCLER
        RecyclerView recyclerViewHandler = view.findViewById(R.id.pd_overview_recycler_handlers);
        RecyclerViewAdapterUser adapterHandler = new RecyclerViewAdapterUser(project.getHandler(), getContext());
        recyclerViewHandler.setAdapter(adapterHandler);
        recyclerViewHandler.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView recyclerViewProcessor = view.findViewById(R.id.pd_overview_recycler_processors);
        RecyclerViewAdapterUser adapterProcessor = new RecyclerViewAdapterUser(project.getProcessor(), getContext());
        recyclerViewProcessor.setAdapter(adapterProcessor);
        recyclerViewProcessor.setLayoutManager(new LinearLayoutManager(getContext()));

        editAndSaveButton.setOnClickListener((View view) -> {
            if (!editMode.get()) {
                editMode.set(true);
                pdTitle.setEnabled(true);
                pdDescription.setEnabled(true);
                spinnerType.setEnabled(true);
                spinnerStatus.setEnabled(true);

                editAndSaveButton.setText("SAVE");
                deleteAndCancelButton.setText("CANCEL");

                editAndSaveButton.setOnClickListener((View view1) -> {

                    // SET THE FORM
                    EditProjectForm editProjectForm = new EditProjectForm();
                    editProjectForm.setName(pdTitle.getText().toString());
                    editProjectForm.setDescription(pdDescription.getText().toString());
                    editProjectForm.setProjectStatus(ProjectStatus.valueOf(spinnerStatus.getSelectedItem().toString()));
                    editProjectForm.setProjectType(ProjectType.valueOf(spinnerType.getSelectedItem().toString()));
                    ArrayList<String> handlerEmails = new ArrayList<>();
                    for (User user : project.getHandler()) {
                        handlerEmails.add(user.getEmail());
                    }
                    editProjectForm.setHandler(handlerEmails);
                    ArrayList<String> processorEmails = new ArrayList<>();
                    for (User user : project.getProcessor()) {
                        processorEmails.add(user.getEmail());
                    }
                    editProjectForm.setProcessor(processorEmails);

                    // send the form with service

                    new Thread(() -> {
                        try {
                            String responseString = new ProjectService().editProject(editProjectForm, project.getId());
                            if (Utils.isSuccess(responseString)) {
                                getActivity().runOnUiThread(() -> {

                                    editMode.set(false);
                                    pdTitle.setEnabled(false);
                                    pdDescription.setEnabled(false);
                                    spinnerStatus.setEnabled(false);
                                    spinnerType.setEnabled(false);
                                    editAndSaveButton.setText("Edit");

                                    pdTitle.setText(editProjectForm.getName());
                                    pdDescription.setText(editProjectForm.getDescription());
                                    spinnerType.setSelection(adapterType.getPosition(editProjectForm.getProjectType().toString()));
                                    spinnerStatus.setSelection(adapterStatus.getPosition(editProjectForm.getProjectStatus().toString()));
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


                });

                deleteAndCancelButton.setOnClickListener((View view1) -> {
                    pdTitle.setEnabled(false);
                    pdDescription.setEnabled(false);
                    spinnerType.setEnabled(false);
                    spinnerStatus.setEnabled(false);
                    editMode.set(false);
                });
            } else {
                pdTitle.setText(project.getName());
                pdDescription.setText(project.getDescription());
                spinnerStatus.setSelection(adapterStatus.getPosition(project.getStatus().toString()));
                spinnerType.setSelection(adapterType.getPosition(project.getType().toString()));

                editMode.set(false);
                pdTitle.setEnabled(false);
                pdDescription.setEnabled(false);
                spinnerType.setEnabled(false);
                spinnerStatus.setEnabled(false);
            }
        });
>>>>>>> Stashed changes

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
