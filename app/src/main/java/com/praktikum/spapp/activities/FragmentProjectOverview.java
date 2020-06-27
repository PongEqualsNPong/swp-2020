package com.praktikum.spapp.activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Project;

public class FragmentProjectOverview extends Fragment {
    EditText pdTitle;
    EditText pdDescription;
    EditText pdStatus;
    EditText pdType;

    Button editProject;
    Button editSave;

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


        return view;


    }
}
