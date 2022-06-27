package com.example.javiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<ItemModel> list;

    public MyAdapter(Context context, ArrayList<ItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemModel itemModel = list.get(position);
        holder.tv_eventName.setText(itemModel.getEventName());
        holder.tv_date.setText(itemModel.getDate());
        holder.tv_status.setText(itemModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_eventName, tv_status, tv_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_eventName = itemView.findViewById(R.id.tv_eventName);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
