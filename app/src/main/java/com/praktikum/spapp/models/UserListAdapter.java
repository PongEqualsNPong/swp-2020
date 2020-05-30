package com.praktikum.spapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.praktikum.spapp.R;
import com.praktikum.spapp.models.enums.Role;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User> {

    private static final String TAG = "PersonListAdapter";

    private Context nContext;
    private int nResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView username;
        TextView email;
        TextView role;
    }

    public UserListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        nContext = context;
        nResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the user information
        String username = getItem(position).getUsername();
        String email = getItem(position).getEmail();
        Role role = getItem(position).getRole();
        String roleString = role.toString();

        User user = new User(username, email, role);



        //create view result
        final View result;

        ViewHolder holder;


        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(nContext);
            convertView = inflater.inflate(nResource, parent, false);
            holder = new ViewHolder();
            holder.username = (TextView) convertView.findViewById(R.id.textView1);
            holder.email = (TextView) convertView.findViewById(R.id.textView3);
            holder.role = (TextView) convertView.findViewById(R.id.textView2);

            result = convertView;

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            result =  convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(nContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.username.setText(username);
        holder.email.setText(email);
        holder.role.setText(roleString);






//        TextView tvUsername = (TextView) convertView.findViewById(R.id.textView1);
//        TextView tvEmail = (TextView) convertView.findViewById(R.id.textView2);
//        TextView tvRole = (TextView) convertView.findViewById(R.id.textView3);
//
//        tvUsername.setText(username);
//        tvEmail.setText(email);
//        tvRole.setText(role.toString() );

        return convertView;

    }
}
