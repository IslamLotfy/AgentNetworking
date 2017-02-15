package com.example.islam.agentnetworking.Presenters;

import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.example.islam.agentnetworking.CallBackPackage.RegisterListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.Interactors.RegisterInteractor;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

/**
 * Created by islam on 15/02/17.
 */

public class RegisterPresenter {

    private RegisterInteractor interactor;
    private Authenticator authenticator;
    private UserModel userModel;
    private DatabaseHelper databaseHelper;

    public RegisterPresenter(){
        interactor=new RegisterInteractor();
        authenticator=Authenticator.getInstance();
        userModel=new UserModel();
        databaseHelper=DatabaseHelper.getInstance();
    }

    public void register(String firstName, String lastName, String email, String pass, String confirmPass, final RegisterListener listener){
        final int check=interactor.validateUserData(firstName,lastName,email,pass,confirmPass);
        if(check==0) {
            authenticator.register(email, pass, new AuthenticationListener() {
                @Override
                public void onAuthSuccess() {
                    listener.onRegisterSuccess();
                    }

                @Override
                public void onAuthFailure() {
                    listener.onRegisterFailure(check);
                    }
                });
        }else{
            listener.onRegisterFailure(check);
        }

    }

    public void writeUser(String firstName, String lastName, String email){
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setUid(authenticator.getUserId());
        databaseHelper.writeUser(userModel);
    }
}
