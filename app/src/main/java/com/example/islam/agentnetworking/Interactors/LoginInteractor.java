package com.example.islam.agentnetworking.Interactors;

/**
 * Created by islam on 15/02/17.
 */

public class LoginInteractor {
    public boolean validateLoginData(String email,String password){
        if(email!=null&& password!= null)
            return true;
        return false;
    }
}
