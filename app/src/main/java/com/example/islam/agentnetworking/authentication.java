package com.example.islam.agentnetworking;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by islam on 08/02/17.
 */

public class authentication   {
    private static authentication instance;
    private authenticationListener listener;
    private FirebaseAuth firebaseAuth;
    private authentication(authenticationListener listener){
        firebaseAuth=FirebaseAuth.getInstance();
        this.listener=listener;
    }
    public void register(String email,String pass){
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }
        });
    }
    public void login(String email,String pass){
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
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

    public void sendVerificationEmail(String email){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }
        });
    }
    public void resetUserPassword(String email){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }
        });
    }
    public static authentication getInstance(authenticationListener listener){
        if(instance==null){
            return new authentication(listener);
        }else
            return instance;
    }
}
