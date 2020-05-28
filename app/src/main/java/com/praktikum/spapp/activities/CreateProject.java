package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;

public class CreateProject extends AppCompatActivity {

    // UI Elements
    EditText textFieldEnterProjectName;
    EditText textFieldEnterProjectDescription;
    Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        // assign UI Elements
        textFieldEnterProjectName = (EditText) findViewById(R.id.enterProjectName);
        textFieldEnterProjectDescription = (EditText) findViewById(R.id.enterProjectDescription);
        buttonCreate = (Button) findViewById(R.id.buttonCreateProject);








    }
}
