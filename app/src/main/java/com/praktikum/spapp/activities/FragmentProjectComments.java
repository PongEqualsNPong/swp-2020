package com.praktikum.spapp.activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.praktikum.spapp.R;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterComment;

import java.util.ArrayList;

public class FragmentProjectComments extends Fragment {

    RecyclerViewAdapterComment adapter;
    ArrayList<Comment> comments;
    Button commentDeleteButton;
    Button commentViewallButton;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_project_comments, container, false);

        view = inflater.inflate(R.layout.fragment_project_comments, container, false);
        Comment comment = (Comment) getArguments().getSerializable("comments");
        Project project = (Project) getArguments().getSerializable("project");



        commentDeleteButton = view.findViewById(R.id.comment_delete_button);
        commentViewallButton = view.findViewById(R.id.comment_viewall_button);


        commentDeleteButton.setOnClickListener((View view) -> {
            try {
                //HARDCODE FOR TESTING
                //String responseString = new CommentService().commentDelete(1);
                String responseString = new CommentService().commentDelete(comment.getId());

                Toast.makeText(getContext(), responseString, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        RecyclerView recyclerView = view.findViewById(R.id.comment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        comments = project.getComments();
        adapter = new RecyclerViewAdapterComment(comments, view.getContext());

        recyclerView.setAdapter(adapter);

        return view;
    }
}
