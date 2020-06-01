package com.praktikum.spapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.adapters.UserListAdapter;

import java.util.ArrayList;

public class ShowFetchedUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fetched_users);

        final ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<User> userArrayList = (ArrayList<User>)getIntent().getSerializableExtra("userArrayList");

        final UserListAdapter adapter = new UserListAdapter(this, R.layout.adapter_view_layout, userArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get user object
                User user =(User) listView.getAdapter().getItem(position);
                //send intent with user
                Intent intent = new Intent(ShowFetchedUsersActivity.this, ShowUserDetailsActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);

            }
        }));
    }
}
