package com.praktikum.spapp.activities;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;

public class FragmentProjectOverview extends Fragment  {
    EditText pdTitle;
    EditText pdDescription;
    EditText pdStatus;
    EditText pdType;

    Button editProject;
    Button editSave;
    Button delete;

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

//        delete.findViewById(R.id.button_deleteProject);
//        delete.setOnClickListener(this);

        return view;


    }

//    public void onClick(View view){
//        ProjectService projectService = new ProjectService();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setMessage("Are you sure?");
//        builder.setCancelable(true);
//        builder.setPositiveButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.cancel();
//                    }
//                }
//        );
//        builder.setNegativeButton("Yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Project project = (Project) getIntent().getSerializableExtra("project");
//
//                        new Thread(() -> {
//                            try {
//                                String resultString = projectService.projectDelete(project);
//                                System.out.println(resultString);
//
//                                if(Utils.isSuccess(resultString)) {
//                                    runOnUiThread(() -> Snackbar.make(view, "done you fuck", Snackbar.LENGTH_LONG));
//                                }
//                                else {
//                                    runOnUiThread(() -> Snackbar.make(view, "sum ting wong", Snackbar.LENGTH_LONG));
//                                }
//                            } catch (Exception e) {
//
//                                runOnUiThread(() -> Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG));
//                            }
//                        }).start();
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}
