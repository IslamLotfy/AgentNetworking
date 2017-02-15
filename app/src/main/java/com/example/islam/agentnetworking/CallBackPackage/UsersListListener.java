package com.example.islam.agentnetworking.CallBackPackage;

import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

import java.util.List;

/**
 * Created by islam on 15/02/17.
 */

public interface UsersListListener {
    public interface ReadUser {
        public void onReadUser(UserModel user);
    }
    public interface ReadusersList {
        public void onReadUsersList(List<ChoiceListModel> users);
    }
}
