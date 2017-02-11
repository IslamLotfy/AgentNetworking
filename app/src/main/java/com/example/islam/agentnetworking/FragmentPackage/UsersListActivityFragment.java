package com.example.islam.agentnetworking.FragmentPackage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.islam.agentnetworking.ActivityPackage.MainActivity;
import com.example.islam.agentnetworking.ActivityPackage.UsersListActivity;
import com.example.islam.agentnetworking.AdaptersPackage.RecyclerChoiceAdapter;
import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.NetworkModel;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;
import com.example.islam.agentnetworking.R;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersListActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ChoiceListModel> users;
    private Authenticator authenticator;
    private DatabaseHelper databaseHelper;
    private Button backButton;
    private Button nextButton;
    private Button skipButton;
    private UserModel userModel;
    private List<UserModel> userModelList;
    private PostModel postModel;

    public UsersListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_users_list, container, false);
        backButton =(Button)view.findViewById(R.id.backbtn);
        nextButton =(Button)view.findViewById(R.id.nextbtn);
        skipButton =(Button)view.findViewById(R.id.skipbtn);

        recyclerView=(RecyclerView)view.findViewById(R.id.networkview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        users=new LinkedList<ChoiceListModel>();
        userModelList=new LinkedList<UserModel>();
        authenticator = Authenticator.getInstance();
        databaseHelper = DatabaseHelper.getInstance();
        databaseHelper.getUser(authenticator.getUserId(),new DataLoadedListener<UserModel>(){
            @Override
            public void onDataLoaded(List<UserModel> result) {
                userModel=result.get(0);
            }
        });
        databaseHelper.readUsers(new DataLoadedListener<UserModel>() {
            @Override
            public void onDataLoaded(List<UserModel> result) {
                userModelList = result;
                for (int i = 0; i < result.size(); i++) {
                    UserModel user=result.get(i);
                    if (user.getNetwork().equals(userModel.getNetwork())) {
                        users.add(user.getChoiceListModel());
                    }
                }
                RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(users);
                recyclerView.setAdapter(recyclerChoiceAdapter);
            }
        });

        RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(users);
        recyclerView.setAdapter(recyclerChoiceAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postModel=(PostModel) getActivity().getIntent().getSerializableExtra("post");
                Toast.makeText(getActivity(),userModel.getEmail(),Toast.LENGTH_LONG).show();

                for (int i = 0; i < users.size(); i++) {
                    UserModel userModel1 = userModelList.get(i);
                    userModel1.setChoiceListModel(users.get(i));
                    if (userModel1.getChoiceListModel().isSelected()) {
                        userModel1.pushPostId(postModel.getPostId());
                        databaseHelper.writeUser(userModel1);
                    }
                }
                nextOne();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextOne();
            }
        });

        return view;
    }


    public void nextOne(){
        Toast.makeText(getActivity(),"post added successfully ",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
