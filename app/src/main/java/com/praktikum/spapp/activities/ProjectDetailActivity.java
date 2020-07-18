package com.praktikum.spapp.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.service.ProjectService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Project;

import static java.lang.Integer.parseInt;

public class ProjectDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Bundle bundle = new Bundle();
    Button button_delete ;


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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    beginActivity).commit();
        }

       // button_delete = findViewById(R.id.projectdetail_buttonDelete);
       // button_delete.setOnClickListener(this);

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

    public void onClick(View view){
        ProjectService projectService = new ProjectService();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Project project = (Project) getIntent().getSerializableExtra("project");

                        new Thread(() -> {
                            try {
                                String resultString = projectService.projectDelete(project);
                                System.out.println(resultString);

                                if(Utils.isSuccess(resultString)) {
                                    runOnUiThread(() -> Snackbar.make(view, "done you fuck", Snackbar.LENGTH_LONG));
                                }
                                else {
                                    runOnUiThread(() -> Snackbar.make(view, "sum ting wong", Snackbar.LENGTH_LONG));
                                }
                            } catch (Exception e) {

                                runOnUiThread(() -> Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG));
                            }
                        }).start();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
