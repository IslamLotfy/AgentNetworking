package com.example.islam.agentnetworking.Interactors;

/**
 * Created by islam on 15/02/17.
 */

public class RegisterInteractor {
    public int validateUserData(String firstName, String lastName, String email, String pass, String confirmPass){
        if(firstName==null || lastName==null || email==null || pass==null || confirmPass ==null)
            return 1;
        if(!pass.equals(confirmPass))
            return 2;
        if(pass.length()<6)
            return 3;
        return 0;
    }
}
