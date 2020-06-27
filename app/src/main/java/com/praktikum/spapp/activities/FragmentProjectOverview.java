package com.praktikum.spapp.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;

public class FragmentProjectOverview extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;

    EditText pdTitle;
    EditText pdDescription;
    EditText pdStatus;
    EditText pdType;

    Button editProject;
    Button editSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_project_overview, container, false);
        Project project = (Project) getArguments().getSerializable("project");
        pdTitle = view.findViewById(R.id.pd_title);
        pdTitle.setText(project.getName());
        pdDescription = view.findViewById(R.id.pd_description);
        pdDescription.setText(project.getDescription());
//        pdStatus = view.findViewById(R.id.pd_status);
//        pdStatus.setText(project.getProjectStatus().toString());
//        pdType = view.findViewById(R.id.pd_type);
//        pdType.setText(project.getType().toString());


        Spinner spinner = view.findViewById(R.id.project_status_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.project_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);


        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

