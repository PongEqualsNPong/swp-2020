package com.praktikum.spapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.User;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class AppointmentDetailsActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_startDate;
    private EditText et_endDate;
    private EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        AtomicBoolean editMode = new AtomicBoolean(false);

        Appointment user = (Appointment) getIntent().getSerializableExtra("appointment");

        //set button
        Button buttonEaC = findViewById(R.id.button_edit_appointment_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_save_appointment);


        //set bind ETs and set values
        et_name = findViewById(R.id.et_name);
        et_name.setText(user.getUsername());

        et_startDate = findViewById(R.id.et_startDate);
        et_startDate.setText(user.getEmail());

        et_endDate = findViewById(R.id.et_endDate);
        et_endDate.setText(user.getUserInfo().getForename());

        et_description = findViewById(R.id.et_description);
        et_description.setText(user.getUserInfo().getSurename());

        matrikelnummer = findViewById(R.id.et_student_number);
        matrikelnummer.setText("" + user.getUserInfo().getStudentNumber());

        studiengang = findViewById(R.id.et_course);
        studiengang.setText(user.getUserInfo().getCourseOfStudy());

        pruefungsordnung = findViewById(R.id.et_ex_regulations);
        pruefungsordnung.setText(user.getUserInfo().getExaminationRegulations());

    }
}