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

import static com.praktikum.spapp.models.enums.Role.ROLE_ADMIN;
import static com.praktikum.spapp.models.enums.Role.ROLE_USER;

public class ShowFetchedUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fetched_users);

        final ListView listView = (ListView) findViewById(R.id.listView);

        User cyberMujahid = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
        User monsoon = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
        User sol = new User("sol","789", "solemail", ROLE_USER);
        User tung = new User("tung","987", "tungemail", ROLE_USER);

        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);
        arrayList.add(cyberMujahid);
        arrayList.add(monsoon);
        arrayList.add(sol);
        arrayList.add(tung);

        final UserListAdapter adapter = new UserListAdapter(this, R.layout.adapter_view_layout, arrayList);
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
