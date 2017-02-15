package com.example.islam.agentnetworking.FragmentPackage;

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

import com.example.islam.agentnetworking.ActivityPackage.MainActivity;
import com.example.islam.agentnetworking.ActivityPackage.RegisterActivity;
import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.example.islam.agentnetworking.CallBackPackage.LoginListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.Presenters.LoginPresenter;
import com.example.islam.agentnetworking.R;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    private EditText mailField;
    private EditText passField;
    private Button loginButton;
    private Button signupButton;
    private Authenticator authenticator;
    private ProgressDialog progressDialog;
    private LoginPresenter presenter;


    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        mailField =(EditText)view.findViewById(R.id.mailfield);
        passField =(EditText)view.findViewById(R.id.passwordfield);
        loginButton =(Button)view.findViewById(R.id.signinbtn);
        signupButton =(Button)view.findViewById(R.id.signup);
        progressDialog =new ProgressDialog(getActivity());
        presenter=new LoginPresenter();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loging();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    public void loging(){
        authenticator = Authenticator.getInstance();
        String mail= mailField.getText().toString();
        String pass= passField.getText().toString();
        progressDialog.setTitle("Loading ");
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        presenter.login(mail, pass, new LoginListener() {
            @Override
            public void onLoginSuccess() {
                progressDialog.dismiss();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onLoginFailure() {
                progressDialog.dismiss();
                Toast.makeText(getActivity()," an error occured\n please try again !",Toast.LENGTH_SHORT);
            }
        });
    }

    public void signup(){
        Intent intent=new Intent(getActivity(),RegisterActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
