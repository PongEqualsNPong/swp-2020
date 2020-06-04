package com.praktikum.spapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import com.praktikum.spapp.R;
import com.praktikum.spapp.Service.UserService;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.InviteForm;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.models.enums.Role;
import org.json.JSONException;

import java.io.IOException;


public class InviteActivity extends AppCompatActivity implements View.OnClickListener {

    //string from xml elems
    String inputEmail;
    Role inputRole;
    int inputProject;
    boolean handler;
    boolean processor;

    //XML elems
    private EditText etInputEmail;
    private EditText etInputProjectId;
    private CheckBox cbIsHandler;
    private CheckBox cbIsProcessor;
    private Switch switchIsAdmin;
    private Button confirm;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        //assign XML elems
        etInputEmail = (EditText) findViewById(R.id.inputUserMail);
        etInputProjectId = (EditText) findViewById(R.id.inputProjectNo);
        cbIsHandler = (CheckBox) findViewById(R.id.isHandler);
        cbIsProcessor = (CheckBox) findViewById(R.id.isProcessor);
        switchIsAdmin = (Switch) findViewById(R.id.isAdmin);

        confirm = (Button) findViewById(R.id.buttonConfirm);
        cancel = (Button) findViewById(R.id.buttonCancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.buttonConfirm:
                InviteForm inviteForm = createInviteForm();
                new Thread (() -> {

                    UserService userService = new UserService();
                    try {

                        String responseString = userService.addUserInvitation(inviteForm);
                        if(Utils.isSuccess(responseString)) {
                            //TODO get invite url, start email app
                        } else {
                            //TODO cancel activity
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();



            case R.id.buttonCancel:

        }
    }

    public InviteForm createInviteForm(){

        InviteForm inviteForm = new InviteForm();
        inviteForm.setEmail(etInputEmail.getText().toString());
        inviteForm.setProjectId(Integer.parseInt(etInputProjectId.getText().toString()));
        if(cbIsHandler.isChecked()){
            inviteForm.setHandler(true);
        } else{
            inviteForm.setHandler(false);
        }
        if(cbIsProcessor.isChecked())
        {
            inviteForm.setProcessor(true);
        } else {
            inviteForm.setProcessor(false);
        }
        return inviteForm;
    }





//    public void startOpenMailView(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri data = Uri.parse("mailto:?subject=" + givenEmail + "&body"+ "hello");
//        intent.setData(data);
//        startActivity(intent);
//    }
}