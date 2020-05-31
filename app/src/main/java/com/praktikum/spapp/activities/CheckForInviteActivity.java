package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;

public class CheckForInviteActivity extends AppCompatActivity implements View.OnClickListener {

    //string from intent
    String inviteKey, firstName, lastName;
    //string from xml elems
    String givenKey, givenFN, givenLN;
    //XML elems
    EditText key, fname, lname;
    Button buttonConfirm, buttonCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_invite);

        Intent intent = getIntent();
        inviteKey = intent.getStringExtra("invite Key");
        firstName = intent.getStringExtra("first Name");
        lastName = intent.getStringExtra("last name");

        //assign XML
        key = (EditText) findViewById(R.id.inputInviteKey);
        fname = (EditText) findViewById(R.id.inputFirstName);
        lname = (EditText) findViewById(R.id.inputLastName);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(this);
        buttonConfirm.setOnClickListener(this);

    }

    public void onClick(View view) {
        givenKey = key.getText().toString();
        givenFN = fname.getText().toString();
        givenLN = lname.getText().toString();

        switch (view.getId()) {
            case R.id.buttonConfirm:
                if ((!givenKey.isEmpty()) && (!givenFN.isEmpty()) && (!givenLN.isEmpty()))
                    startCheckForInvite2(view);
                break;

            case R.id.buttonCancel:
                key.setText(" ");
                fname.setText(" ");
                lname.setText(" ");

                break;

        }
    }

    public void startCheckForInvite2(View view) {
        Intent intent = new Intent(this, CheckForInvite2.class);
        startActivity(intent);

    }
}