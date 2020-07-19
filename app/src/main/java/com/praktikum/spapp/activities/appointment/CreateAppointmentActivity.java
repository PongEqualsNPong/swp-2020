package com.praktikum.spapp.activities.appointment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.general.WelcomeActivity;
import com.praktikum.spapp.activities.project.FragmentProjectAppointments;
import com.praktikum.spapp.activities.project.FragmentProjectOverview;
import com.praktikum.spapp.activities.project.ProjectDetailActivity;
import com.praktikum.spapp.common.DateStringSplitter;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.service.AppointmentService;
import com.praktikum.spapp.service.internal.AppointmentServiceImpl;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateAppointmentActivity extends AppCompatActivity {

    private EditText ct_name;
    private EditText ct_startDate;
    private EditText ct_endDate;
    private EditText ct_startTime;
    private EditText ct_endTime;
    private EditText ct_description;
    private DatePickerDialog dateStartPicker;
    private TimePickerDialog timeStartPicker;
    private DatePickerDialog dateEndPicker;
    private TimePickerDialog timeEndPicker;
    private Button button_create_appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        AppointmentService appointmentService = new AppointmentServiceImpl(SessionManager.getSession());
        Project project = (Project) getIntent().getSerializableExtra("project");
        Spinner ct_types = (Spinner) findViewById(R.id.ct_types);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ct_types.setAdapter(adapter);

        AtomicBoolean editMode = new AtomicBoolean(false);

        ct_startDate = (EditText) findViewById(R.id.ct_startDate);
        ct_startDate.setInputType(InputType.TYPE_NULL);
        ct_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dateStartPicker = new DatePickerDialog(CreateAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                ct_startDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        }, year, month, day);
                dateStartPicker.show();
            }
        });

        ct_startTime = (EditText) findViewById(R.id.ct_startTime);
        ct_startTime.setInputType(InputType.TYPE_NULL);
        ct_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timeStartPicker = new TimePickerDialog(CreateAppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                ct_startTime.setText(sHour + ":" + sMinute + " Uhr");
                            }
                        }, hour, minutes, true);
                timeStartPicker.show();
            }
        });

        ct_endDate = (EditText) findViewById(R.id.ct_endDate);
        ct_endDate.setInputType(InputType.TYPE_NULL);
        ct_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dateEndPicker = new DatePickerDialog(CreateAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                ct_endDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        }, year, month, day);
                dateEndPicker.show();
            }
        });

        ct_endTime = (EditText) findViewById(R.id.ct_endTime);
        ct_endTime.setInputType(InputType.TYPE_NULL);
        ct_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timeEndPicker = new TimePickerDialog(CreateAppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                ct_endTime.setText(sHour + ":" + sMinute + " Uhr");
                            }
                        }, hour, minutes, true);
                timeEndPicker.show();
            }
        });
        button_create_appointment = findViewById(R.id.button_create_appointment);
        ct_name = findViewById(R.id.ct_name);
        ct_description = findViewById(R.id.ct_description);
        button_create_appointment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                JsonObject data = new JsonObject();
                data.addProperty("name", ct_name.getText().toString());
                data.addProperty("description", ct_description.getText().toString());
                data.addProperty("startDate", DateStringSplitter.changeToDateFormat(ct_startDate.getText().toString(), ct_startTime.getText().toString(), view.getContext()));
                data.addProperty("endDate", DateStringSplitter.changeToDateFormat(ct_endDate.getText().toString(), ct_endTime.getText().toString(), view.getContext()));
                if(!(ct_types.getSelectedItem().toString().toUpperCase().equals("NONE"))) {
                    data.addProperty("type", ct_types.getSelectedItem().toString().toUpperCase());
                }
                new Thread(() -> {
                    try {
                        appointmentService.createAppointment(data, project.getId());
                        runOnUiThread(() -> {
                            Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
                            intent.putExtra("project", project);
                            intent.putExtra("changed", true);
                            startActivity(intent);
                        });
                    } catch (ResponseException e) {
                        runOnUiThread(() -> {
                            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                        });
                    }
                }).start();
            }
        });

    }
}