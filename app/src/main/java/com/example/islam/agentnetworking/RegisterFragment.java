package com.example.islam.agentnetworking;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment implements authenticationListener {

    private EditText emailfield;
    private EditText passfield;
    private EditText confirmfield;
    private Button signupbtn;
    private Authentication auth;
    private ProgressDialog pd;
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        emailfield=(EditText) view.findViewById(R.id.emailfield);
        passfield=(EditText)view.findViewById(R.id.passfield);
        confirmfield=(EditText)view.findViewById(R.id.confirmfield);
        signupbtn=(Button)view.findViewById(R.id.signupbtn);
        auth=Authentication.getInstance(this);
        pd=new ProgressDialog(getContext());

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    @Override
    public void onSuccess() {
        pd.dismiss();
        Toast.makeText(getContext(),"You are registred succfully !",Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(),"You are not registred ! \n please try again !",Toast.LENGTH_LONG).show();
    }
    public void signup(){
        String mail=emailfield.getText().toString();
        String pass=passfield.getText().toString();
        String confirmpass=confirmfield.getText().toString();
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
