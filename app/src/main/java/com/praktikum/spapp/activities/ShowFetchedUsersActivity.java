package com.praktikum.spapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterUser;

import java.io.IOException;
import java.util.ArrayList;

public class ShowFetchedUsersActivity extends AppCompatActivity {
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fetched_users);

        new Thread(() -> {
            try {
                UserService userService = new UserService();
                this.userArrayList = userService.fetchAllUsers();

                runOnUiThread(() -> {
                    try {
                        initRecyclerView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                //TODO
            } catch (Exception e) {
                //TODO
            }
        }).start();
    }

    private void initRecyclerView() throws IOException {
        UserService userService = new UserService();
        RecyclerView recyclerView = findViewById(R.id.user_recycler_view);
        RecyclerViewAdapterUser adapter = new RecyclerViewAdapterUser(userArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}