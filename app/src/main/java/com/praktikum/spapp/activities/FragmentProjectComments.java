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

import com.praktikum.spapp.R;
import com.praktikum.spapp.service.internal.CommentServiceImpl;
import com.praktikum.spapp.models.Comment;

public class FragmentProjectComments extends Fragment {

    Button commentDeleteButton;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_project_comments, container, false);

        view = inflater.inflate(R.layout.fragment_project_comments, container, false);
        Comment comment = (Comment) getArguments().getSerializable("comments");


        commentDeleteButton = view.findViewById(R.id.comment_delete_button);


        commentDeleteButton.setOnClickListener((View view) -> {
            try {
                //HARDCODE FOR TESTING
                //String responseString = new CommentService().commentDelete(1);
                String responseString = new CommentServiceImpl().commentDelete(comment.getId());

                Toast.makeText(getContext(), responseString, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return view;
    }
}
