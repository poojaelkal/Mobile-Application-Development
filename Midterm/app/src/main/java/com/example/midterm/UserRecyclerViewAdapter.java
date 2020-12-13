package com.example.midterm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.AppViewHolder> {

    public static String TAG="demo";
    ArrayList<User> users;

    public UserRecyclerViewAdapter(ArrayList<User> data){
        this.users = new ArrayList<User>();
        this.users = data;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_list, parent, false);
        AppViewHolder userViewHolder = new AppViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewAdapter.AppViewHolder holder, int position) {
        User user = users.get(position);
        holder.textName.setText(user.name);
        holder.textGroup.setText(user.group);
        holder.textAge.setText(String.valueOf(user.age));
        holder.textState.setText(user.state);
        if(user.gender.equals("Male")){
            holder.imageGender.setImageResource(R.drawable.avatar_male);
        }else{
            holder.imageGender.setImageResource(R.drawable.avatar_female);
        }

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textGroup;
        TextView textState;
        TextView textAge;
        ImageView imageGender;
        View rootView;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            textName = itemView.findViewById(R.id.textName);
            textGroup = itemView.findViewById(R.id.textGroup);
            textState = itemView.findViewById(R.id.textState);
            textAge = itemView.findViewById(R.id.textAge);
            imageGender = itemView.findViewById(R.id.imageGender);

        }
    }
}
