package com.praktikum.spapp.activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.UserListAdapter;

import java.util.ArrayList;

import static com.praktikum.spapp.models.enums.Role.ROLE_ADMIN;
import static com.praktikum.spapp.models.enums.Role.ROLE_USER;

public class ShowFetchedUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fetched_users);

        ListView listView = (ListView) findViewById(R.id.listView);

        User cyberMujahid = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
        User monsoon = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
        User sol = new User("sol","789", "solemail", ROLE_USER);
        User tung = new User("tung","987", "tungemail", ROLE_USER);
//        User cyberMujahid1 = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
//        User monsoon1 = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
//        User sol1 = new User("sol","789", "solemail", ROLE_USER);
//        User tung1 = new User("tung","987", "tungemail", ROLE_USER);
//        User cyberMujahid2 = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
//        User monsoon2 = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
//        User sol2 = new User("sol","789", "solemail", ROLE_USER);
//        User tung2 = new User("tung","987", "tungemail", ROLE_USER);
//        User cyberMujahid3 = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
//        User monsoon3 = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
//        User sol3 = new User("sol","789", "solemail", ROLE_USER);
//        User tung3 = new User("tung","987", "tungemail", ROLE_USER);
//        User cyberMujahid4 = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
//        User monsoon4 = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
//        User sol4 = new User("sol","789", "solemail", ROLE_USER);
//        User tung4 = new User("tung","987", "tungemail", ROLE_USER);
//        User cyberMujahid5 = new User("cyber","1234", "cyberemail", ROLE_ADMIN);
//        User monsoon5 = new User("monsoon","4321", "monsoonemail", ROLE_ADMIN);
//        User sol5 = new User("sol","789", "solemail", ROLE_USER);
//        User tung5 = new User("tung","987", "tungemail", ROLE_USER);


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

        UserListAdapter adapter = new UserListAdapter(this, R.layout.adapter_view_layout, arrayList);
        listView.setAdapter(adapter);

    }
}
