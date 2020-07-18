package com.praktikum.spapp.activities;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.common.Utils;
import com.praktikum.spapp.models.Token;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.service.AuthenticationService;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterComment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentProjectComments extends Fragment {

    RecyclerViewAdapterComment adapter;
    ArrayList<Comment> comments;
    Button commentDeleteButton;
    Button commentViewallButton;
    Button commenSetRestrictedButton;
    Button commentSetPublicButton;
    View view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_project_comments, container, false);

        view = inflater.inflate(R.layout.fragment_project_comments, container, false);
        Comment comment = (Comment) getArguments().getSerializable("comments");
        Project project = (Project) getArguments().getSerializable("project");



        commentDeleteButton = view.findViewById(R.id.comment_delete_button);
        commentViewallButton = view.findViewById(R.id.comment_viewall_button);


        RecyclerView recyclerView = view.findViewById(R.id.comment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        comments = project.getComments();
        // filter out all the restricted comments
        //comments.removeIf(x -> x.isRestricted());
        AuthenticationService authenticationService = new AuthenticationService();
        Token resBody = authenticationService.getToken();
        User thisUser = resBody.getCurrentUser();
        System.out.println(thisUser.getRoles().get(0));
        if(thisUser.getRoles().get(0).toString().equals("ROLE_USER"))
        {
            comments.removeIf(x -> x.isRestricted());
        }

        adapter = new RecyclerViewAdapterComment(comments, view.getContext());

        recyclerView.setAdapter(adapter);

        return view;
    }
}
