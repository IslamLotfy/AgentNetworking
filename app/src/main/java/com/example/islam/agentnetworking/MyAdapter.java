package com.example.islam.agentnetworking;

/**
 * Created by islam on 07/02/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by islam on 02/02/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<stringHolder> list;

    public MyAdapter(List<stringHolder> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        stringHolder choice = list.get(position);
        holder.setData(choice);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
