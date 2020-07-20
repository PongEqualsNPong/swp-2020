package com.praktikum.spapp.activities.comment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.project.ProjectDetailActivity;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.service.AppointmentService;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.service.internal.AppointmentServiceImpl;
import com.praktikum.spapp.service.internal.CommentServiceImpl;

public class CreateCommentActivity extends AppCompatActivity {

    private Switch isPublic;
    private Button button_create_appointment;
    private EditText ct_comment;
    private CommentService commentService = new CommentServiceImpl(SessionManager.getSession());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);
        Project project = (Project) getIntent().getSerializableExtra("project");
        button_create_appointment = (Button) findViewById(R.id.button_create_appointment);
        isPublic = (Switch) findViewById(R.id.isPublic);
        ct_comment = (EditText) findViewById(R.id.ct_comment);
        button_create_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean isSwitchSetted = isPublic.isChecked();
            String commentText = ct_comment.getText().toString();
            new Thread(() -> {
                try {
                    commentService.createComment(project.getId(), isSwitchSetted, commentText);
                    runOnUiThread(() -> {
                        Intent intent = new Intent(v.getContext(), ProjectDetailActivity.class);
                        intent.putExtra("project", project);
                        intent.putExtra("changed", false);
                        intent.putExtra("createdComment", true);
                        startActivity(intent);
                    });

                } catch (ResponseException e) {
                    Snackbar.make(v, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }).start();
            }
        });
    }
}