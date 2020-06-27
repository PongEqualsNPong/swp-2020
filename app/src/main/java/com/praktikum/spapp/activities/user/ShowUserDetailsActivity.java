package com.praktikum.spapp.activities.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.AuthenticationService;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.User;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class ShowUserDetailsActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText vorname;
    private EditText nachname;
    private EditText matrikelnummer;
    private EditText studiengang;
    private EditText pruefungsordnung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);
        AtomicBoolean editMode = new AtomicBoolean(false);


        User user = (User) getIntent().getSerializableExtra("user");

        //set button
        Button buttonEaC = findViewById(R.id.button_edit_user_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_edit_user_save);


        //set bind ETs and set values
        username = findViewById(R.id.et_username);
        username.setText(user.getUsername());

        email = findViewById(R.id.et_email);
        email.setText(user.getEmail());

        vorname = findViewById(R.id.et_first_name);
        vorname.setText(user.getUserInfo().getForename());

        nachname = findViewById(R.id.et_last_name);
        nachname.setText(user.getUserInfo().getSurename());

        matrikelnummer = findViewById(R.id.et_student_number);
        matrikelnummer.setText("" + user.getUserInfo().getStudentNumber());

        studiengang = findViewById(R.id.et_course);
        studiengang.setText(user.getUserInfo().getCourseOfStudy());

        pruefungsordnung = findViewById(R.id.et_ex_regulations);
        pruefungsordnung.setText(user.getUserInfo().getExaminationRegulations());

        // on clicklisteners
        buttonEaC.setOnClickListener((View view) -> {

            if (!editMode.get()) {
                editMode.set(true);
                username.setEnabled(true);
                email.setEnabled(true);
                vorname.setEnabled(true);
                nachname.setEnabled(true);
                matrikelnummer.setEnabled(true);

                buttonEaC.setText("Cancel");
                buttonEditSave.setVisibility(View.VISIBLE);


                buttonEditSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditUserForm editUserForm = new EditUserForm();
                        editUserForm.setUserEditByEmail(email.getText().toString());
                        editUserForm.setEmail(email.getText().toString());
                        editUserForm.setUsername(username.getText().toString());
                        editUserForm.setForename(vorname.getText().toString());
                        editUserForm.setSurname(nachname.getText().toString());
                        editUserForm.setStudentNumber(parseInt(matrikelnummer.getText().toString()));
                        editUserForm.setCourseOfStudy(studiengang.getText().toString());
                        editUserForm.setExaminationRegulations(pruefungsordnung.getText().toString());

                        new Thread(() -> {
                            try {
                                String responseString = new UserService().editUser(editUserForm);
                                if (Utils.isSuccess(responseString)) {
                                    runOnUiThread(() -> {

                                        editMode.set(false);
                                        username.setEnabled(false);
                                        email.setEnabled(false);
                                        vorname.setEnabled(false);
                                        nachname.setEnabled(false);
                                        matrikelnummer.setEnabled(false);
                                        buttonEaC.setText("Edit");
                                        buttonEditSave.setVisibility(View.GONE);

                                        username.setText(editUserForm.getUsername());
                                        email.setText(editUserForm.getEmail());
                                        vorname.setText(editUserForm.getForename());
                                        nachname.setText(editUserForm.getSurname());
                                        matrikelnummer.setText(Integer.toString(editUserForm.getStudentNumber()));
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
                username.setText(user.getUsername());
                email.setText(user.getEmail());
                vorname.setText(user.getUserInfo().getForename());
                nachname.setText(user.getUserInfo().getSurename());
                matrikelnummer.setText("" + user.getUserInfo().getStudentNumber());

                editMode.set(false);
                username.setEnabled(false);
                email.setEnabled(false);
                vorname.setEnabled(false);
                nachname.setEnabled(false);
                matrikelnummer.setEnabled(false);
                buttonEaC.setText("Edit");
                buttonEditSave.setVisibility(View.GONE);


            }



        });
    }
}
