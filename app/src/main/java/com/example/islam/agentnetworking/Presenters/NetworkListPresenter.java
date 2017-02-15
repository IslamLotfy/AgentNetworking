package com.example.islam.agentnetworking.Presenters;

import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.CallBackPackage.NetworkListListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.NetworkModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 15/02/17.
 */

public class NetworkListPresenter {

    private DatabaseHelper databaseHelper;
    private List<NetworkModel> networkModels;

    public NetworkListPresenter(){
        databaseHelper=DatabaseHelper.getInstance();
        networkModels=new LinkedList<>();
    }

    public void readNetworks(final NetworkListListener listener){
        databaseHelper.readNetworks(new DataLoadedListener<NetworkModel>() {
            @Override
            public void onDataLoaded(List<NetworkModel> result) {
                networkModels=result;
                List<ChoiceListModel> nets=new LinkedList<ChoiceListModel>();
                for(int i=0 ; i<result.size();i++){
                    nets.add(result.get(i).getChoiceListModel());
                }
                listener.onNetworkRead(nets);
            }
        });
    }

    public void updateNetworks(List<ChoiceListModel> networks ,String postId){
        for(int i=0 ; i<networks.size();i++){
            ChoiceListModel choiceListModel=networks.get(i);
            NetworkModel net=networkModels.get(i);
            net.setChoiceListModel(choiceListModel);
            if(choiceListModel.isSelected()){
                net.pushPosetId(postId);
                databaseHelper.writeNetwork(net);
            }
        }
    }
}
