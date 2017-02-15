package com.example.islam.agentnetworking.Presenters;

import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.CallBackPackage.UsersListListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 15/02/17.
 */

public class UsersListPresenter {

    private List<UserModel> userModelList;
    private DatabaseHelper databaseHelper;
    private UserModel userModel;
    private Authenticator authenticator;


    public UsersListPresenter(){
        databaseHelper=DatabaseHelper.getInstance();
        authenticator=Authenticator.getInstance();
        userModel=new UserModel();
        userModelList=new LinkedList<>();
    }

    public void readUsersList(final UsersListListener.ReadusersList listener){
        final List<ChoiceListModel> choiceListModels=new LinkedList<>();
        databaseHelper.readUsers(new DataLoadedListener<UserModel>() {
            @Override
            public void onDataLoaded(List<UserModel> result) {
                userModelList=result;
                for(int i=0 ; i<result.size();i++){
                    UserModel user=result.get(i);
                    if(user.getNetwork().equals(userModel.getNetwork()))
                        choiceListModels.add(user.getChoiceListModel());
                }
                listener.onReadUsersList(choiceListModels);
            }
        });
    }

    public void updateUsers(List<ChoiceListModel> choiceListModels,String postId){
        for(int i=0 ; i<choiceListModels.size();i++){
            ChoiceListModel c=choiceListModels.get(i);
            UserModel user=userModelList.get(i);
            user.setChoiceListModel(c);
            if(c.isSelected()){
                user.pushPostId(postId);
                databaseHelper.writeUser(user);
            }
        }
    }

    public void readUser(final UsersListListener.ReadUser listener){
        databaseHelper.getUser(authenticator.getUserId(), new DataLoadedListener<UserModel>() {
            @Override
            public void onDataLoaded(List<UserModel> result) {
                userModel=result.get(0);
                listener.onReadUser(userModel);
            }
        });
    }
}
