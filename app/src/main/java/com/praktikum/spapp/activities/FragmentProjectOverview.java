package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterUser;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentProjectOverview extends Fragment {
    View view;
    EditText pdTitle;
    EditText pdDescription;
    Spinner spinnerType;
    Spinner spinnerStatus;

    Button editAndSaveButton;
    Button deleteAndCancelButton;

    int editMode = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_overview, container, false);
        Project project = (Project) getArguments().getSerializable("project");

        // Set Values
        pdTitle = view.findViewById(R.id.pd_overview_et_title);
        pdTitle.setText(project.getName());
        pdDescription = view.findViewById(R.id.pd_overview_et_description);
        pdDescription.setText(project.getDescription());

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
            switch (editMode) {
                case 0:
                    // edit
                    pdTitle.setEnabled(true);
                    pdDescription.setEnabled(true);
                    spinnerType.setEnabled(true);
                    spinnerStatus.setEnabled(true);
                    editAndSaveButton.setText("SAVE");
                    deleteAndCancelButton.setText("CANCEL");
                    editMode += 1;
                    break;

                case 1:
                    // save
                    // SET THE FORM

                    JSONObject editForm = new JSONObject();
                    try {
                        if (pdTitle.getText() != null) editForm.put("name", pdTitle.getText().toString());
                        if (pdDescription.getText() != null)
                            editForm.put("description", pdDescription.getText().toString());
                        if (spinnerType.getSelectedItem().toString().equalsIgnoreCase("none"))
                            editForm.put("projectType", spinnerType.getSelectedItem().toString());
                        if (spinnerStatus.getSelectedItem().toString().equalsIgnoreCase("none"))
                            editForm.put("projectStatus", spinnerStatus.getSelectedItem().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new Thread(() -> {

                        try {
                            String responseString = new ProjectService().editProject(editForm, project.getId());
                            if (Utils.isSuccess(responseString)) {
                                getActivity().runOnUiThread(() -> {

                                    pdTitle.setEnabled(false);
                                    pdDescription.setEnabled(false);
                                    spinnerStatus.setEnabled(false);
                                    spinnerType.setEnabled(false);
                                    editAndSaveButton.setText("EDIT");
                                    deleteAndCancelButton.setText("DELETE");


                                    Snackbar.make(view, "Con fuckign gratys, your changes were saved.", Snackbar.LENGTH_LONG).show();
                                    editMode -= 1;
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
                    break;
            }
        });


        deleteAndCancelButton.setOnClickListener((View view) -> {
            switch (editMode) {
                case 0:
                    // delete

                case 1:
                    // cancel
                    pdTitle.setEnabled(false);
                    pdDescription.setEnabled(false);
                    spinnerType.setEnabled(false);
                    spinnerStatus.setEnabled(false);

                    editMode -= 1;
                    deleteAndCancelButton.setText("DELETE");
                    editAndSaveButton.setText("EDIT");
                    pdTitle.setText(project.getName());
                    pdDescription.setText(project.getDescription());
                    spinnerStatus.setSelection(adapterStatus.getPosition(project.getStatus().toString()));
                    spinnerType.setSelection(adapterType.getPosition(project.getType().toString()));
            }
        });
        return view;
    }
}







