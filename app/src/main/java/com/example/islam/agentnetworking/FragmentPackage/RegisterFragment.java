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

import com.example.islam.agentnetworking.ActivityPackage.LoginActivity;
import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.example.islam.agentnetworking.CallBackPackage.RegisterListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.Presenters.RegisterPresenter;
import com.example.islam.agentnetworking.R;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment  {

    private EditText emailField;
    private EditText passField;
    private EditText confirmField;
    private Button signupButton;
    private EditText nameField1;
    private EditText nameField2;
    private ProgressDialog progressDialog;
    private String mail;
    private String pass;
    private String confirmPass;
    private String firstName;
    private String secondName;
    private RegisterPresenter presenter;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        emailField =(EditText) view.findViewById(R.id.emailfield);
        passField =(EditText)view.findViewById(R.id.passfield);
        confirmField =(EditText)view.findViewById(R.id.confirmfield);
        nameField1 =(EditText)view.findViewById(R.id.firstname);
        nameField2 =(EditText)view.findViewById(R.id.secondname);
        signupButton =(Button)view.findViewById(R.id.signupbtn);
        progressDialog =new ProgressDialog(getActivity());
        presenter=new RegisterPresenter();
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        return view;
    }

    public void signup() {
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("please wait...");
        progressDialog.show();
        mail = emailField.getText().toString();
        pass = passField.getText().toString();
        confirmPass = confirmField.getText().toString();
        firstName = nameField1.getText().toString();
        secondName = nameField2.getText().toString();
        presenter.register(firstName, secondName, mail, pass, confirmPass, new RegisterListener() {
            @Override
            public void onRegisterSuccess() {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "your are registered now",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onRegisterFailure(int type) {
                progressDialog.dismiss();
                switch (type){
                    case 0:Toast.makeText(getActivity(), "an error occured \n please try again !",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:Toast.makeText(getActivity(), "please fill all the fields !", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:Toast.makeText(getActivity(), "please check your password and confirmation field !", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:Toast.makeText(getActivity(), "password should be at least 6 characters!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
