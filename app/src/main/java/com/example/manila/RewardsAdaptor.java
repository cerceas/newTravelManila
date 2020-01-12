package com.example.manila;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RewardsAdaptor extends RecyclerView.Adapter<RewardsAdaptor.ViewHolder> {
    private ArrayList<Rewards> rewards;
    private DatabaseHelperForUsers mydb;
    private int userID;
    public RewardsAdaptor(Context context, ArrayList<Rewards> list,int userID){
        rewards =list;
        this.userID=userID;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView Icons;
        TextView Title;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.TextViewTitleRewards);
            Icons=itemView.findViewById(R.id.ImageViewRewards);
            layout=itemView.findViewById(R.id.LayoutRewards);

        }
    }

    @NonNull
    @Override
    public RewardsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewardslist,parent,false);
        mydb=new DatabaseHelperForUsers(v.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int level=mydb.getLevel(userID);
        holder.itemView.setTag(rewards.get(position));
        holder.Title.setText(rewards.get(position).getName());
        if(holder.Title.getText().equals("FREE MCFLOAT AND FRIEST")) {
            if(level<2) {
                holder.layout.setClickable(false);
                return;
            }
        }else if(holder.Title.getText().equals("FREE Girlfriend for 1 hour")){
            if(level<3){
                holder.layout.setClickable(false);
                return;
            }
        }else if(holder.Title.getText().equals("FREE GOSURF 50 at Globe")){
            if(level<4){
                holder.layout.setClickable(false);
                return;
            }
        }else if(holder.Title.getText().equals("FREE BigBite at 7-11")){
            if(level<5){
                holder.layout.setClickable(false);
                return;
            }
        }else if(holder.Title.getText().equals("FREE Test me")){
            if(level<6){
                holder.layout.setClickable(false);
                return;
            }
        }
        if(rewards.get(position).getPreference().equals("FREE MCFLOAT AND FRIEST")){
            holder.Icons.setImageResource(R.drawable.friesanddrinks);
        }else if(rewards.get(position).getPreference().equals("FREE Girlfriend for 1 hour")){
            holder.Icons.setImageResource(R.drawable.girlfriend);
        }else if(rewards.get(position).getPreference().equals("FREE GOSURF 50 at Globe")){
            holder.Icons.setImageResource(R.drawable.globe);
        }else if(rewards.get(position).getPreference().equals("FREE BigBite at 7-11")){
            holder.Icons.setImageResource(R.drawable.seveneleven);
        }else if(rewards.get(position).getPreference().equals("FREE Test me")){
            holder.Icons.setImageResource(R.drawable.girlfriend);
        }

    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }
}
