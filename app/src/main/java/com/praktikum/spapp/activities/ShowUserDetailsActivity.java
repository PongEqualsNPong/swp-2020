package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;

public class ShowUserDetailsActivity extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        User user = (User) getIntent().getSerializableExtra("User");

        username = (TextView) findViewById(R.id.textView4);
        username.setText(user.getUsername());



    }
}
