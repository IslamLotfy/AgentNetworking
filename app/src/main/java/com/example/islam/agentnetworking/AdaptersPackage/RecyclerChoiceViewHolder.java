package com.example.islam.agentnetworking.AdaptersPackage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.R;

/**
 * Created by islam on 07/02/17.
 */

public class RecyclerChoiceViewHolder extends RecyclerView.ViewHolder {

    private CheckBox checkBox;
    private View mView;
    private static int cnt=0;
    public RecyclerChoiceViewHolder(View itemView) {
        super(itemView);
        mView=itemView;
    }
    public void setData(final ChoiceListModel item){
        checkBox=(CheckBox)mView.findViewById(R.id.checkBox1);
        checkBox.setText(item.getName());
        checkBox.setSelected(item.isSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSelected(checkBox.isChecked());
                if(item.isSelected())cnt++;
                else cnt--;
                System.out.println(cnt+"   hollaaa  ");
            }
        });
    }
}
