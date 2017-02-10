package com.example.islam.agentnetworking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment implements authenticationListener,Databaselistener {

    private EditText emailfield;
    private EditText passfield;
    private EditText confirmfield;
    private Button signupbtn;
    private EditText namefield1;
    private EditText namefield2;
    private Authentication auth;
    private ProgressDialog pd;
    private String mail;
    private String pass;
    private String confirmpass;
    private String firstname;
    private String secondname;
    private User user;
    private Databasehelper databasehelper;
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        emailfield=(EditText) view.findViewById(R.id.emailfield);
        passfield=(EditText)view.findViewById(R.id.passfield);
        confirmfield=(EditText)view.findViewById(R.id.confirmfield);
        namefield1=(EditText)view.findViewById(R.id.firstname);
        namefield2=(EditText)view.findViewById(R.id.secondname);
        signupbtn=(Button)view.findViewById(R.id.signupbtn);
        pd=new ProgressDialog(getContext());
        databasehelper=Databasehelper.getInstance(this);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    @Override
    public void onAuthSuccess() {
        pd.dismiss();
        user=new User(firstname,secondname,auth.getUserId(),"");
        databasehelper.writeUser(user);
        Toast.makeText(getContext(),auth.getUserId(),Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getContext(),Login.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onDatabaseSuccess(List<stringHolder> list) {

    }

    @Override
    public void onDatabaseFailure() {

    }

    @Override
    public void onAuthFailure() {
        Toast.makeText(getContext(),"You are not registred ! \n please try again !",Toast.LENGTH_LONG).show();
    }
    public void signup(){
         auth=Authentication.getInstance(this);
         mail=emailfield.getText().toString();
         pass=passfield.getText().toString();
         confirmpass=confirmfield.getText().toString();
         firstname=namefield1.getText().toString();
         secondname=namefield2.getText().toString();
        if(!mail.equals("")&&!pass.equals("")&&!confirmpass.equals("")){
            if(pass.equals(confirmpass)){
                if(pass.length()>=6){
                pd.setTitle("Loading");
                pd.setMessage("please wait...");
                pd.show();
                auth.register(mail,pass);
                }else{
                    Toast.makeText(getContext(),"password should be at least 6 characters!",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getContext(),"please check your password !",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getContext(),"please type your email and password !",Toast.LENGTH_LONG).show();
        }
    }
}
