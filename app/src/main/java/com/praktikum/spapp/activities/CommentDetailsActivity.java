package com.praktikum.spapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.User;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class CommentDetailsActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText vorname;
    private EditText nachname;
    private EditText matrikelnummer;
    private EditText studiengang;
    private EditText pruefungsordnung;

    private EditText commentContent;
    private EditText commentDate;
    private Switch commentIsRestricted;
    private Switch commentIsEdited;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment_details);
        AtomicBoolean editMode = new AtomicBoolean(false);


        Comment comment = (Comment) getIntent().getSerializableExtra("comment");

        //set button
        Button buttonEaC = findViewById(R.id.button_edit_comment_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_edit_comment_save);


        //set bind ETs and set values
        username = findViewById(R.id.et_username);
        username.setText(comment.getAuthor().getUsername());

        email = findViewById(R.id.et_email);
        email.setText(comment.getAuthor().getEmail());

        vorname = findViewById(R.id.et_first_name);
        vorname.setText(comment.getAuthor().getUserInfo().getForename());

        nachname = findViewById(R.id.et_last_name);
        nachname.setText(comment.getAuthor().getUserInfo().getSurname());

        matrikelnummer = findViewById(R.id.et_student_number);
        matrikelnummer.setText("" + comment.getAuthor().getUserInfo().getStudentNumber());

        studiengang = findViewById(R.id.et_course);
        studiengang.setText(comment.getAuthor().getUserInfo().getCourseOfStudy());

        pruefungsordnung = findViewById(R.id.et_ex_regulations);
        pruefungsordnung.setText(comment.getAuthor().getUserInfo().getExaminationRegulations());

        commentContent = findViewById(R.id.et_comment_content);
        commentContent.setText(comment.getContent());


        commentDate = findViewById(R.id.et_comment_date);
        commentDate.setText(comment.getCreationTime());

        commentIsRestricted = findViewById(R.id.switch_comment_restricted);
        commentIsRestricted.setChecked(comment.isRestricted());

        commentIsEdited = findViewById(R.id.switch_comment_edited);
        commentIsEdited.setChecked(comment.isEdited());

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
                                        buttonEaC.setText("Edit - dont click");
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
                username.setText(comment.getAuthor().getUsername());
                email.setText(comment.getAuthor().getEmail());
                vorname.setText(comment.getAuthor().getUserInfo().getForename());
                nachname.setText(comment.getAuthor().getUserInfo().getSurname());
                matrikelnummer.setText("" + comment.getAuthor().getUserInfo().getStudentNumber());

                editMode.set(false);
                username.setEnabled(false);
                email.setEnabled(false);
                vorname.setEnabled(false);
                nachname.setEnabled(false);
                matrikelnummer.setEnabled(false);
                buttonEaC.setText("Edit - dont click");
                buttonEditSave.setVisibility(View.GONE);


            }



        });
    }
}
