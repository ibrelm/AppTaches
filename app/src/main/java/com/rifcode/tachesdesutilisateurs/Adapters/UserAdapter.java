package com.rifcode.tachesdesutilisateurs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rifcode.tachesdesutilisateurs.Models.User;
import com.rifcode.tachesdesutilisateurs.R;
import com.rifcode.tachesdesutilisateurs.Views.TachesActivity;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{


    private final ArrayList<User> mUserList;
    private final Context mContext;

    public UserAdapter(ArrayList<User> usersList, Context mContext){
        this.mUserList = usersList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View view = layout.inflate(R.layout.row_user_layout,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tvName.setText(mUserList.get(position).getName());
        holder.tvUsername.setText(mUserList.get(position).getUsername());
        holder.tvEmail.setText(mUserList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvUsername,tvEmail;

        public UserViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvEmail = itemView.findViewById(R.id.tvEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();

                    if(pos!= RecyclerView.NO_POSITION){

                        Intent intent =  new Intent(mContext, TachesActivity.class);
                        intent.putExtra("user_id",mUserList.get(pos).getId());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);

                    }

                }
            });

        }
    }

}
