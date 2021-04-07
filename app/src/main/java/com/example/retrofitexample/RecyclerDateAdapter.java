package com.example.retrofitexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDateAdapter extends RecyclerView.Adapter<RecyclerDateAdapter.DateViewHolder> {
        Context ctx;
        List<Repo> list;

    public RecyclerDateAdapter(Context ctx, List<Repo> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DateViewHolder(LayoutInflater.from(ctx).inflate(R.layout.mylayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
    holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        public DateViewHolder(@NonNull View itemView) {
            super(itemView);

            date =itemView.findViewById(R.id.tv_date);

        }
    }
}
