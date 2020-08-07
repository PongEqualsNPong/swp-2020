package com.praktikum.spapp.activities.comment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.appointment.CreateAppointmentActivity;
import com.praktikum.spapp.activities.comment.CreateCommentActivity;
import com.praktikum.spapp.common.SessionManager;
import com.praktikum.spapp.exception.ResponseException;
import com.praktikum.spapp.models.User;
import com.praktikum.spapp.service.AppointmentService;
import com.praktikum.spapp.service.AuthenticationService;
import com.praktikum.spapp.models.Comment;
import com.praktikum.spapp.models.Project;
import com.praktikum.spapp.models.adapters.RecyclerViewAdapterComment;
import com.praktikum.spapp.service.CommentService;
import com.praktikum.spapp.service.UserService;
import com.praktikum.spapp.service.internal.AppointmentServiceImpl;
import com.praktikum.spapp.service.internal.CommentServiceImpl;
import com.praktikum.spapp.service.internal.UserServiceImpl;

import java.util.ArrayList;

public class FragmentProjectComments extends Fragment {

    CommentService commentService = new CommentServiceImpl(SessionManager.getSession());


    UserService service = new UserServiceImpl(SessionManager.getSession());
    RecyclerViewAdapterComment adapter;
    ArrayList<Comment> comments;
    Button commentDeleteButton;
    Button commentViewallButton;
    Button commenSetRestrictedButton;
    Button commentSetPublicButton;
    View view;
    Button buttonCreateComment;
    Button buttonToolTip;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_project_comments, container, false);


        view = inflater.inflate(R.layout.fragment_project_comments, container, false);
        Comment comment = (Comment) getArguments().getSerializable("comments");
        Project project = (Project) getArguments().getSerializable("project");
        boolean createdComment = (boolean) getArguments().getSerializable("createdComment");
        setHasOptionsMenu(true);
        buttonCreateComment = view.findViewById(R.id.button_create_comment);
        buttonCreateComment.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CreateCommentActivity.class);
            intent.putExtra("project", project);
            startActivity(intent);
        });
        commentDeleteButton = view.findViewById(R.id.comment_delete_button);
        //commentViewallButton = view.findViewById(R.id.comment_viewall_button);

        RecyclerView recyclerView = view.findViewById(R.id.comment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        comments = project.getComments();
        // filter out all the restricted comments
        //comments.removeIf(x -> x.isRestricted());
        /*AuthenticationService authenticationService = new AuthenticationService();
        Token resBody = authenticationService.getToken();
        User thisUser = resBody.getCurrentUser();
        */
        new Thread(() -> {
            User currentUser;
            try {
                currentUser = service.getUserByUsername(SessionManager.getSession().getCurrentUsername());
                System.out.println(currentUser.getRoles().get(0));
                if (currentUser.getRoles().get(0).toString().equals("ROLE_USER")) {
                    comments.removeIf(x -> x.isRestricted());
                }
                getActivity().runOnUiThread(() -> {
                    adapter = new RecyclerViewAdapterComment(comments, view.getContext());
                    if (createdComment) {
                        Snackbar.make(view, "Your comment have been created.", Snackbar.LENGTH_LONG).show();
                    }
                    recyclerView.setAdapter(adapter);
                });
            } catch (ResponseException e) {
                e.printStackTrace();
            }

        }).start();


        buttonToolTip = view.findViewById(R.id.ProjectComment_tooltip);
        buttonToolTip.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage("To filter out Restricted comment, simply type \"restricted\" on the search bar." +
                    "           \n To filter out unrestricted comments, type \"not restricted \" ");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "OKAY",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    }
            );

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        UserService userService = new UserServiceImpl(SessionManager.getSession());
        User currentUser;

//        try {
//            if (Utils.isEmail(SessionManager.getSession().getCurrentUsername())) {
//                currentUser = userService.getUserByEmail(SessionManager.getSession().getCurrentUsername());
//            } else {
//                currentUser = userService.getUserByUsername(SessionManager.getSession().getCurrentUsername());
//            }
//        } catch (ResponseException e) {
//            currentUser = null;
//        }
//
//
//        boolean isAdmin = currentUser.getRoles()
//                .stream()
//                .noneMatch(x -> x.equals(Role.ROLE_USER));
//        if (!isAdmin) {
//            try {
//                comments = commentService.getPublicComments(project.getId());
//            } catch (ResponseException e) {
//                e.printStackTrace();
//            }
//        }
        recyclerView.setAdapter(adapter);

//        commentDeleteButton.setOnClickListener((view) -> {
//            try {
//                commentService.deleteComment(comment.getId());
//                getActivity().runOnUiThread(() -> Snackbar.make(view, "Comment succesfully deleted.", Snackbar.LENGTH_LONG));
//            } catch (ResponseException e) {
//                getActivity().runOnUiThread(() -> Snackbar.make(view, "Deleting comment failed", Snackbar.LENGTH_LONG));
//            }
//        });
        return view;
    }

    /**
     * Create option menu with search bar
     * @param menu
     * @param inflater
     */

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.user_filter_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

    }
}
