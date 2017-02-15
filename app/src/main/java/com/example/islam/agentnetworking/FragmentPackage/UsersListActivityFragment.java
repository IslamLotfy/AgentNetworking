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
import com.example.islam.agentnetworking.AdaptersPackage.RecyclerChoiceAdapter;
import com.example.islam.agentnetworking.CallBackPackage.UsersListListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;
import com.example.islam.agentnetworking.Presenters.UsersListPresenter;
import com.example.islam.agentnetworking.R;

import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsersListActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ChoiceListModel> choiceListModels;
    private Button backButton;
    private Button nextButton;
    private Button skipButton;
    private UserModel userModel;
    private PostModel postModel;
    private UsersListPresenter presenter;

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

        choiceListModels =new LinkedList<ChoiceListModel>();
        userModel=new UserModel();
        presenter=new UsersListPresenter();
        presenter.readUser(new UsersListListener.ReadUser() {
            @Override
            public void onReadUser(UserModel user) {
                userModel=user;
            }
        });

        presenter.readUsersList(new UsersListListener.ReadusersList() {
            @Override
            public void onReadUsersList(List<ChoiceListModel> users) {
                choiceListModels=users;
                RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(users);
                recyclerView.setAdapter(recyclerChoiceAdapter);
            }
        });

        RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(choiceListModels);
        recyclerView.setAdapter(recyclerChoiceAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postModel=(PostModel) getActivity().getIntent().getSerializableExtra("post");
                presenter.updateUsers(choiceListModels,postModel.getPostId());
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
