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

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements authenticationListener,Databaselistener {

    private EditText mailfield;
    private EditText passfield;
    private Button loginbtn;
    private Button signupbtn;
    private Authentication auth;
    private ProgressDialog pd;


    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        mailfield=(EditText)view.findViewById(R.id.mailfield);
        passfield=(EditText)view.findViewById(R.id.passwordfield);
        loginbtn=(Button)view.findViewById(R.id.signinbtn);
        signupbtn=(Button)view.findViewById(R.id.signup);
        pd=new ProgressDialog(getContext());
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loging();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    public void loging(){
        auth=Authentication.getInstance(this);
        String mail=mailfield.getText().toString();
        String pass=passfield.getText().toString();
        if(mail!=null&&pass!=null){
            pd.setTitle("Loading ");
            pd.setMessage("please wait...");
            pd.show();
            auth.login(mail,pass);
        }else{
            Toast.makeText(getContext(),"please check your mail and password !",Toast.LENGTH_LONG);
        }
    }

    public void signup(){
        Intent intent=new Intent(getContext(),Register.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void onAuthSuccess() {
        pd.dismiss();
        Intent intent=new Intent(getContext(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onAuthFailure() {
        pd.dismiss();
        Toast.makeText(getContext()," an error occured\n please try again !",Toast.LENGTH_LONG);
    }



    @Override
    public void onDatabaseSuccess(List<stringHolder> list) {

    }

    @Override
    public void onDatabaseFailure() {

    }
}
