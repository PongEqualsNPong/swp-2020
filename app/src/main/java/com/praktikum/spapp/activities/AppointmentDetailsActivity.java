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
    private EditText et_description;
    private EditText eText;
    private TextView tvw;
    private DatePickerDialog picker;
    private  TextView tvwT;
    private EditText eTextT;
    private TimePickerDialog pickerT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        Spinner spinner = (Spinner) findViewById(R.id.et_types);
        spinner.setEnabled(false);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.appointment_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        tvw = (TextView) findViewById(R.id.textView1);
        eText = (EditText) findViewById(R.id.et_startDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AppointmentDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        tvwT = (TextView) findViewById(R.id.timeView);
        EditText eTextT = (EditText) findViewById(R.id.et_startTime);
        eTextT.setInputType(InputType.TYPE_NULL);
        eTextT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                pickerT = new TimePickerDialog(AppointmentDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eTextT.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickerT.show();
            }
        });





        AtomicBoolean editMode = new AtomicBoolean(false);

        Appointment appointment = (Appointment) getIntent().getSerializableExtra("appointment");

        //set button
        Button buttonEaC = findViewById(R.id.button_edit_appointment_and_cancel);
        Button buttonEditSave = findViewById(R.id.button_save_appointment);


        //set bind ETs and set values
        et_name = findViewById(R.id.et_name);
        et_name.setText(appointment.getName());

        et_startDate = findViewById(R.id.et_startDate);
        et_startDate.setText(DateStringSplitter.datePrettyPrint(appointment.getStartDate()));

        et_endDate = findViewById(R.id.et_endDate);
        et_endDate.setText(DateStringSplitter.datePrettyPrint(appointment.getEndDate()));

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
                spinner.setEnabled(true);

                buttonEaC.setText("Cancel");
                buttonEditSave.setVisibility(View.VISIBLE);


                buttonEditSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditAppointmentForm editAppointForm = new EditAppointmentForm();
                        editAppointForm.setName(et_name.getText().toString());
                        editAppointForm.setDescription(et_startDate.getText().toString());
                        editAppointForm.setStartDate(et_endDate.getText().toString());
                        editAppointForm.setEndDate(et_description.getText().toString());



                        new Thread(() -> {
                            try {
                               // String responseString = new AppointmentsService().editUser(editAppointForm);
                               // if (Utils.isSuccess(responseString)) {
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
                                        matrikelnummer.setText(Integer.toString(editUserForm.getStudentNumber()));
                                        Snackbar.make(view, "Con fuckign gratys, your changes were saved.", Snackbar.LENGTH_LONG).show();*/


                                    });
                               /* } else {
                                    runOnUiThread(() -> {
                                        Snackbar.make(view, Utils.parseForJsonObject(responseString, "Error"), Snackbar.LENGTH_LONG).show();
                                    });

                                }*/

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
                spinner.setEnabled(false);
                buttonEaC.setText("Edit");
                buttonEditSave.setVisibility(View.GONE);


            }

        });

    }
}