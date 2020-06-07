package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;

public class ShowUserDetailsActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvEmail;
    TextView tvRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        User user = (User) getIntent().getSerializableExtra("user");

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvUsername.setText(user.getUsername());
        tvRole.setText(user.getRole().toString());
        tvEmail.setText(user.getEmail());
    }
}
