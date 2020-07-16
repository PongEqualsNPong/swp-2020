package com.praktikum.spapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.User;

import org.json.JSONException;
import org.json.JSONObject;

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

    private Button commentDeleteButton;
    private Button commenSetRestrictedButton;
    private Button commentSetPublicButton;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comment_details);
        AtomicBoolean editMode = new AtomicBoolean(false);


        Comment comment = (Comment) getIntent().getSerializableExtra("comment");


        /** not using edit for now
        Button buttonEaC = findViewById(R.id.button_edit_comment_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_edit_comment_save);
         */

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

        commentDeleteButton = findViewById(R.id.comment_delete_button);
        commenSetRestrictedButton = findViewById(R.id.comment_set_restricted);
        commentSetPublicButton = findViewById(R.id.comment_set_public);

        /**
        buttonEaC.setOnClickListener((View view) -> {

            if (!editMode.get()) {
                editMode.set(true);
                //username.setEnabled(true);

                buttonEaC.setText("Cancel");
                buttonEditSave.setVisibility(View.VISIBLE);


                buttonEditSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditUserForm editUserForm = new EditUserForm();
                        //editUserForm.setUsername(username.getText().toString());

                        new Thread(() -> {
                            try {
                                String responseString = new UserService().editUser(editUserForm);
                                if (Utils.isSuccess(responseString)) {
                                    runOnUiThread(() -> {

                                        editMode.set(false);
                                        //username.setEnabled(false);
                                        buttonEaC.setText("Edit - dont click");
                                        buttonEditSave.setVisibility(View.GONE);

                                        //username.setText(editUserForm.getUsername());
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
                //username.setText(comment.getAuthor().getUsername());

                editMode.set(false);
                //username.setEnabled(false);
                buttonEaC.setText("Edit - dont click");
                buttonEditSave.setVisibility(View.GONE);


            }



        });
        */


        commentDeleteButton.setOnClickListener((View view) -> {
            new Thread(() -> {
                try {
                    //HARDCODE FOR TESTING
                    String responseString = new CommentService().commentDelete(comment.getId());

                    if (Utils.isSuccess(responseString)) {
                        Snackbar.make(view, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(view, Utils.parseForJsonObject(responseString, "Error"), Snackbar.LENGTH_LONG).show();
                    }                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });


        commenSetRestrictedButton.setOnClickListener((View view1) -> {
            new Thread(() -> {
                try {
                    comment.setRestricted(true);
                    String responseString = new CommentService().commentEdit(comment.getId(),comment);
                    if (Utils.isSuccess(responseString)) {
                        Snackbar.make(view1, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(view1, Utils.parseForJsonObject(responseString, "An error has occurred"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        });

        commentSetPublicButton.setOnClickListener((View view1) -> {
            new Thread(() -> {
                try {
                    comment.setRestricted(false);
                    String responseString = new CommentService().commentEdit(comment.getId(),comment);
                    if (Utils.isSuccess(responseString)) {
                        Snackbar.make(view1, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(view1, Utils.parseForJsonObject(responseString, "An error has occurred"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        });
    }
}
