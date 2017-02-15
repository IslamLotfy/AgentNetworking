package com.example.islam.agentnetworking.CallBackPackage;

import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;

import java.util.List;

/**
 * Created by islam on 15/02/17.
 */

public interface NetworkListListener {
    public void onNetworkRead(List<ChoiceListModel> nets);
}
