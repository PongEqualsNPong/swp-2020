package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;


public class InviteActivity extends AppCompatActivity implements View.OnClickListener {
    //string from intent
    String userEmail, userRole, userProject;
    //string from xml elems
    String givenEmail, givenRole, givenProject;
    //XML elems
    EditText mail, role, project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        //custom fields
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email@user");
        userRole = intent.getStringExtra("user/Role");
        userProject = intent.getStringExtra("user/Project");

        //assign XML elems
        mail = (EditText) findViewById(R.id.inputUserMail);
        role = (EditText) findViewById(R.id.inputUserRole);
        project = (EditText) findViewById(R.id.inputUserProject);

        Button confirm = (Button) findViewById(R.id.buttonConfirm);
        Button cancel = (Button) findViewById(R.id.buttonCancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    public void onClick(View view) {
        givenEmail = mail.getText().toString();
        givenRole = role.getText().toString();
        givenProject = project.getText().toString();

        switch (view.getId()) {

            case R.id.buttonConfirm: {
                if ((!givenEmail.isEmpty()) && (!givenRole.isEmpty()))
                    startOpenMailView(view);

            break;
            }

            case R.id.buttonCancel: {
                mail.setText(" ");
                role.setText(" ");
                project.setText(" ");
            }
        }



    }

    public void startOpenMailView(View view) {
        Intent intent = new Intent(this, openMailActivity.class);
        startActivity(intent);
    }
}