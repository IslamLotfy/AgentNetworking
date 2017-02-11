package com.example.islam.agentnetworking.AdaptersPackage;

/**
 * Created by islam on 07/02/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.R;

import java.util.List;


/**
 * Created by islam on 02/02/17.
 */

public class RecyclerChoiceAdapter extends RecyclerView.Adapter<RecyclerChoiceViewHolder> {
    private List<ChoiceListModel> list;

    public RecyclerChoiceAdapter(List<ChoiceListModel> list) {
        this.list = list;
    }

    @Override
    public RecyclerChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecyclerChoiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerChoiceViewHolder holder, int position) {
        ChoiceListModel choice = list.get(position);
        holder.setData(choice);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
