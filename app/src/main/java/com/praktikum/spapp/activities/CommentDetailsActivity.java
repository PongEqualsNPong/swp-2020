package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.common.DateStringSplitter;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.common.Utils;


import java.util.concurrent.atomic.AtomicBoolean;


public class CommentDetailsActivity extends AppCompatActivity {

    private TextView username;
    private TextView email;
    private TextView vorname;
    private TextView nachname;
    private TextView matrikelnummer;
    private TextView studiengang;
    private TextView pruefungsordnung;

    private EditText commentContent;
    private TextView commentDate;
    private Switch commentIsRestricted;
    private Switch commentIsEdited;

    private Button button_edit_comment_and_cancel;
    private Button button_edit_comment_save;

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
        commentDate.setText(DateStringSplitter.datePrettyPrint(comment.getCreationTime()) + " " + DateStringSplitter.timePrettyPrint(comment.getCreationTime()));

        commentIsRestricted = findViewById(R.id.switch_comment_restricted);
        commentIsRestricted.setChecked(comment.isRestricted());
        commentIsRestricted.setClickable(false);

        commentIsEdited = findViewById(R.id.switch_comment_edited);
        commentIsEdited.setChecked(comment.isWasEdited());
        commentIsEdited.setClickable(false);

        commentDeleteButton = findViewById(R.id.comment_delete_button);
        commenSetRestrictedButton = findViewById(R.id.comment_set_restricted);
        commentSetPublicButton = findViewById(R.id.comment_set_public);

        button_edit_comment_and_cancel = findViewById(R.id.button_edit_comment_and_cancel);
        button_edit_comment_save = findViewById(R.id.button_edit_comment_save);
        button_edit_comment_and_cancel.setOnClickListener((View view) -> {

            if (!editMode.get()) {
                editMode.set(true);
                commentContent.setEnabled(true);
                button_edit_comment_and_cancel.setText("Cancel");
                button_edit_comment_save.setVisibility(View.VISIBLE);


                button_edit_comment_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comment.setContent(commentContent.getText().toString());
                        commentIsEdited.setChecked(comment.isWasEdited());
                        new Thread(() -> {
                            try {
                                String responseString = new CommentService().commentEdit(comment.getId(),comment);
                                if (Utils.isSuccess(responseString)) {
                                    runOnUiThread(() -> {
                                        editMode.set(false);
                                        commentContent.setEnabled(false);

                                        button_edit_comment_and_cancel.setText("Edit");
                                        button_edit_comment_save.setVisibility(View.GONE);

                                        Snackbar.make(view, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                                        commentIsEdited.setChecked(true);
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
                commentContent.setText(comment.getContent());

                editMode.set(false);
                commentContent.setEnabled(false);

                button_edit_comment_and_cancel.setText("Edit");
                button_edit_comment_save.setVisibility(View.GONE);
            }
        });


        commentDeleteButton.setOnClickListener((View view) -> {
            new Thread(() -> {
                try {
                    String responseString = new CommentService().commentDelete(comment.getId());
                    if (Utils.isSuccess(responseString)) {
                        Snackbar.make(view, "Your changes were saved.", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(view, Utils.parseForJsonObject(responseString, "Error"), Snackbar.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
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
                        Looper.prepare();
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            public void run() {
                                commentIsRestricted.setChecked(comment.isRestricted());
                            }
                        });
                        Looper.loop();
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
                        Looper.prepare();
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            public void run() {
                                commentIsRestricted.setChecked(comment.isRestricted());
                            }
                        });
                        Looper.loop();
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
