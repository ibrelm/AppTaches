package com.rifcode.tachesdesutilisateurs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rifcode.tachesdesutilisateurs.Models.Tache;
import com.rifcode.tachesdesutilisateurs.R;

import java.util.ArrayList;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.TacheViewHolder>{


    private final ArrayList<Tache> mTacheList;
    private final Context mContext;

    public TacheAdapter(ArrayList<Tache> tachesList, Context mContext){
        this.mTacheList = tachesList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TacheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(parent.getContext());
        View view = layout.inflate(R.layout.row_tache_layout,parent,false);

        return new TacheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacheViewHolder holder, int position) {
        if (mTacheList.size()>0){
            holder.tvTitle.setText(mTacheList.get(position).getTitle());
            if (mTacheList.get(position).getCompleted()){
                holder.imgCompleted.setVisibility(View.VISIBLE);
            }else{
                holder.imgCompleted.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mTacheList.size();
    }

    class TacheViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView imgCompleted;

        public TacheViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgCompleted = itemView.findViewById(R.id.imgvCompleted);

        }
    }

}
