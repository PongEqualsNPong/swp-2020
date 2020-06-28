package com.praktikum.spapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.AppointmentsService;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.common.DateStringSplitter;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Appointment;
import com.praktikum.spapp.models.EditAppointmentForm;
import com.praktikum.spapp.models.EditUserForm;
import com.praktikum.spapp.models.User;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class AppointmentDetailsActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_startDate;
    private EditText et_endDate;
    private EditText et_startTime;
    private EditText et_endTime;
    private EditText et_description;
    private DatePickerDialog dateStartPicker;
    private TimePickerDialog timeStartPicker;
    private DatePickerDialog dateEndPicker;
    private TimePickerDialog timeEndPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        Spinner et_types = (Spinner) findViewById(R.id.et_types);
        et_types.setEnabled(false);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        et_types.setAdapter(adapter);

        AtomicBoolean editMode = new AtomicBoolean(false);

        Appointment appointment = (Appointment) getIntent().getSerializableExtra("appointment");

        //set button
        Button buttonEaC = findViewById(R.id.button_edit_appointment_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_save_appointment);


        et_startDate = (EditText) findViewById(R.id.et_startDate);
        et_startDate.setInputType(InputType.TYPE_NULL);
        et_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dateStartPicker = new DatePickerDialog(AppointmentDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_startDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        }, DateStringSplitter.yearPrettyPrint(appointment.getStartDate()), DateStringSplitter.monthPrettyPrint(appointment.getStartDate()), DateStringSplitter.dayPrettyPrint(appointment.getStartDate()));
                dateStartPicker.show();
            }
        });

        et_startTime = (EditText) findViewById(R.id.et_startTime);
        et_startTime.setInputType(InputType.TYPE_NULL);
        et_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timeStartPicker = new TimePickerDialog(AppointmentDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                et_startTime.setText(sHour + ":" + sMinute + " Uhr");
                            }
                        },DateStringSplitter.hourPrettyPrint(appointment.getStartDate()), DateStringSplitter.minutePrettyPrint(appointment.getStartDate()), true);
                timeStartPicker.show();
            }
        });

        et_endDate = (EditText) findViewById(R.id.et_endDate);
        et_endDate.setInputType(InputType.TYPE_NULL);
        et_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dateEndPicker = new DatePickerDialog(AppointmentDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_endDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                            }
                        },DateStringSplitter.yearPrettyPrint(appointment.getEndDate()), DateStringSplitter.monthPrettyPrint(appointment.getEndDate()), DateStringSplitter.dayPrettyPrint(appointment.getEndDate()));
                dateEndPicker.show();
            }
        });

        et_endTime = (EditText) findViewById(R.id.et_endTime);
        et_endTime.setInputType(InputType.TYPE_NULL);
        et_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timeEndPicker = new TimePickerDialog(AppointmentDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                et_endTime.setText(sHour + ":" + sMinute + " Uhr");
                            }
                        }, DateStringSplitter.hourPrettyPrint(appointment.getEndDate()), DateStringSplitter.minutePrettyPrint(appointment.getEndDate()), true);
                timeEndPicker.show();
            }
        });

        //set bind ETs and set values
        et_name = findViewById(R.id.et_name);
        et_name.setText(appointment.getName());

        et_startDate = findViewById(R.id.et_startDate);
        et_startDate.setText(DateStringSplitter.datePrettyPrint(appointment.getStartDate()));

        et_endDate = findViewById(R.id.et_endDate);
        et_endDate.setText(DateStringSplitter.datePrettyPrint(appointment.getEndDate()));

        et_startTime = findViewById(R.id.et_startTime);
        et_startTime.setText(DateStringSplitter.timePrettyPrint(appointment.getStartDate()));

        et_endTime = findViewById(R.id.et_endTime);
        et_endTime.setText(DateStringSplitter.timePrettyPrint(appointment.getEndDate()));

        et_description = findViewById(R.id.et_description);
        et_description.setText(appointment.getDescription());

        // on clicklisteners
        buttonEaC.setOnClickListener((View view) -> {

            if (!editMode.get()) {
                editMode.set(true);
                et_name.setEnabled(true);
                et_startDate.setEnabled(true);
                et_endDate.setEnabled(true);
                et_description.setEnabled(true);
                et_startTime.setEnabled(true);
                et_endTime.setEnabled(true);
                et_types.setEnabled(true);

                buttonEaC.setText("Cancel");
                buttonEditSave.setVisibility(View.VISIBLE);


                buttonEditSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditAppointmentForm editAppointForm = new EditAppointmentForm();
                        editAppointForm.setName(et_name.getText().toString());
                        editAppointForm.setType(et_types.getSelectedItem().toString());
                        editAppointForm.setStartDate(DateStringSplitter.changeToDateFormat(et_startDate.getText().toString(),et_startTime.getText().toString()));
                        editAppointForm.setEndDate(DateStringSplitter.changeToDateFormat(et_endDate.getText().toString(),et_endTime.getText().toString()));
                        editAppointForm.setDescription(et_description.getText().toString());

                        String bodyJson = "";
                        bodyJson += "{";
                        bodyJson += "\"name\": \"" +editAppointForm.getName() + "\",";
                        bodyJson += "\"description\": \"" +editAppointForm.getDescription() + "\",";
                        bodyJson += "\"startDate\": \"" +editAppointForm.getStartDate() + "\",";
                        bodyJson += "\"endDate\": \"" +editAppointForm.getEndDate() + "\"";
                        if(!(editAppointForm.getType().equals("None"))) {
                            bodyJson += ",\"type\": \"" + editAppointForm.getType().toUpperCase() + "\"";
                        }
                        bodyJson += "}";


                        String finalBodyJson = bodyJson;
                        new Thread(() -> {
                            try {
                               String responseString = new AppointmentsService().appointmentUpdate(finalBodyJson,appointment.getId());
                                if (Utils.isSuccess(responseString)) {
                                    runOnUiThread(() -> {

                                        editMode.set(false);
                                        et_name.setEnabled(false);
                                        et_startDate.setEnabled(false);
                                        et_endDate.setEnabled(false);
                                        et_description.setEnabled(false);

                                        buttonEaC.setText("Edit");
                                        buttonEditSave.setVisibility(View.GONE);

                                      /*  username.setText(editUserForm.getUsername());
                                        email.setText(editUserForm.getEmail());
                                        vorname.setText(editUserForm.getForename());
                                        nachname.setText(editUserForm.getSurname());
                                        matrikelnummer.setText(Integer.toString(editUserForm.getStudentNumber()));*/
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

                et_name.setText(appointment.getName());
                et_startDate.setText(DateStringSplitter.datePrettyPrint(appointment.getStartDate()));
                et_endDate.setText(DateStringSplitter.datePrettyPrint(appointment.getEndDate()));
                et_description.setText(appointment.getDescription());


                editMode.set(false);
                et_name.setEnabled(false);
                et_startDate.setEnabled(false);
                et_endDate.setEnabled(false);
                et_description.setEnabled(false);
                et_startTime.setEnabled(false);
                et_endTime.setEnabled(false);
                et_types.setEnabled(false);
                buttonEaC.setText("Edit");
                buttonEditSave.setVisibility(View.GONE);


            }

        });

    }
}