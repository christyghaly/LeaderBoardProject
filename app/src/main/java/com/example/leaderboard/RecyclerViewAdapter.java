package com.example.leaderboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<PersonData> mData;
    public int type=1;

    public RecyclerViewAdapter(Context context, ArrayList<PersonData> data,int t) {
        mContext = context;
        mData = data;
        type=t;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_learning,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).name);
        if(type==0) {
            holder.tv_hours.setText(Integer.toString(mData.get(position).number) + " Learning hours, ");
        }
        else{
            holder.tv_hours.setText(Integer.toString(mData.get(position).number) + " skill IQ Score, ");

        }
        holder.tv_country.setText(mData.get(position).country+".");
        Picasso.get().load(mData.get(position).badge).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //keeps ref. to any of the views that we are going to have to set at runtime
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_hours;
        private TextView tv_country;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=(TextView) itemView.findViewById(R.id.name);
            tv_hours=(TextView) itemView.findViewById(R.id.learning_hours);
            tv_country=(TextView) itemView.findViewById(R.id.country);
            img=(ImageView) itemView.findViewById(R.id.learning_badge);

        }
    }
}
