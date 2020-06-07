package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;

public class ShowUserDetailsActivity extends AppCompatActivity {

    private TextView username;
    private TextView email;
    private TextView vorname;
    private TextView nachname;
    private TextView matrikelnummer;
    private TextView studiengang;
    private TextView pruefungsordnung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        User user = (User) getIntent().getSerializableExtra("user");

        username = (TextView) findViewById(R.id.username);
        username.setText(user.getUsername());

        email = (TextView) findViewById(R.id.email);
        email.setText(user.getEmail());

        vorname = (TextView) findViewById(R.id.vorname);
        vorname.setText(user.getUserInfo().getForename());

        nachname = (TextView) findViewById(R.id.nachname);
        nachname.setText(user.getUserInfo().getSurename());

       matrikelnummer = (TextView) findViewById(R.id.matrikelnummer);
       matrikelnummer.setText("" + user.getUserInfo().getStudentNumber());


        studiengang = (TextView) findViewById(R.id.studiengang);
        studiengang.setText(user.getUserInfo().getCourseOfStudy());

        pruefungsordnung = (TextView) findViewById(R.id.pruefungsordnung);
        pruefungsordnung.setText(user.getUserInfo().getExaminationRegulations());


    }
}
