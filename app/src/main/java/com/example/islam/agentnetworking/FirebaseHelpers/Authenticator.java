package com.example.islam.agentnetworking.FirebaseHelpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by islam on 08/02/17.
 */

public class Authenticator {
    private static Authenticator instance=null;
    private FirebaseAuth firebaseAuth;
    private Authenticator(){
        firebaseAuth=FirebaseAuth.getInstance();

    }
    public void register(String email, String pass , final AuthenticationListener listener){
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
    public void login(String email, String pass, final AuthenticationListener listener){
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

    public static Authenticator getInstance(){
        if(instance==null){
            instance=new Authenticator();
            return instance;
        }else
            return instance;
    }
}
