package com.example.islam.agentnetworking.Presenters;

import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.example.islam.agentnetworking.CallBackPackage.LoginListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.Interactors.LoginInteractor;

/**
 * Created by islam on 15/02/17.
 */

public class LoginPresenter {
    private LoginInteractor interactor;
    private Authenticator authenticator;

    public LoginPresenter(){
        interactor=new LoginInteractor();
        authenticator=Authenticator.getInstance();
    }

    public void login(String email, String password, final LoginListener listener){
        if(interactor.validateLoginData(email,password)){
            authenticator.login(email, password, new AuthenticationListener() {
                @Override
                public void onAuthSuccess() {
                    listener.onLoginSuccess();
                }

                @Override
                public void onAuthFailure() {
                    listener.onLoginFailure();
                }
            });
        }else {
            listener.onLoginFailure();
        }
    }
}
