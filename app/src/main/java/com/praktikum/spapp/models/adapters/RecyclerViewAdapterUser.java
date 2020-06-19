package com.praktikum.spapp.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.praktikum.spapp.R;
import com.praktikum.spapp.activities.user.ShowUserDetailsActivity;
import com.praktikum.spapp.models.User;

import java.util.ArrayList;

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<User> users;
    private Context aContext;

    public RecyclerViewAdapterUser(ArrayList<User> users, Context aContext) {
        this.users = users;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder:  called.");

        viewHolder.projectName.setText(users.get(i).getUsername());

        viewHolder.parentLayout.setOnClickListener(view -> {
            Intent intent = new Intent(aContext, ShowUserDetailsActivity.class);
            intent.putExtra("user", users.get(i));
            aContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView projectName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.name_project);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
