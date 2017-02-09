package com.example.islam.agentnetworking;

import android.content.Context;
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
                    listener.onSuccess();
                }else{
                    listener.onFailure();
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

//    public void sendVerificationEmail(String email){
//        String code = UUID.randomUUID().toString();
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("message/rfc822");
//        i.putExtra(Intent.EXTRA_EMAIL, email);
//        i.putExtra(Intent.EXTRA_SUBJECT, "confirmation code ");
//        i.putExtra(Intent.EXTRA_TEXT, code);
//        try {
//             context.startActivity(Intent.createChooser(i, "Send mail..."));
//        } catch (android.content.ActivityNotFoundException ex) {
//            listener.onFailure();
//        }
//    }
//    public void resetUserPassword(String email){
//        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    listener.onSuccess();
//                }else{
//                    listener.onFailure();
//                }
//            }
//        });
//    }
    public static Authentication getInstance(authenticationListener listener){
        if(instance==null){
            return new Authentication(listener);
        }else
            return instance;
    }
}
