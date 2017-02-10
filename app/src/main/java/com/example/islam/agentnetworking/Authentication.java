package com.example.islam.agentnetworking;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by islam on 08/02/17.
 */

public class Authentication {
    private static Authentication instance=null;
    private authenticationListener listener;
    private FirebaseAuth firebaseAuth;
    private Authentication(authenticationListener listener){
        firebaseAuth=FirebaseAuth.getInstance();
        this.listener=listener;
    }
    public void register(String email,String pass){
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onAuthSuccess();
                }else{
                    listener.onAuthFailure();
                }
            }
        });
    }
    public void login(String email,String pass){
        Log.w("TAG.....  " , email+"  000  "+pass);
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onAuthSuccess();
                }else{
                    listener.onAuthFailure();
                }
            }
        });
        

    }
    public void logout(){
        firebaseAuth.signOut();
    }
    public String getUserId(){
       return firebaseAuth.getCurrentUser().getUid();
    }
    public String getNetworkId(){
       return firebaseAuth.getCurrentUser().getUid();
    }

    public static Authentication getInstance(authenticationListener listener){
        if(instance==null){
            instance=new Authentication(listener);
            return instance;
        }else
            return instance;
    }
}
