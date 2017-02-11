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

import com.example.islam.agentnetworking.ActivityPackage.UsersListActivity;
import com.example.islam.agentnetworking.CallBackPackage.AuthenticationListener;
import com.example.islam.agentnetworking.FirebaseHelpers.Authenticator;
import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.ChoiceListModel;
import com.example.islam.agentnetworking.ModelsPackage.NetworkModel;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;
import com.example.islam.agentnetworking.R;
import com.example.islam.agentnetworking.AdaptersPackage.RecyclerChoiceAdapter;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkListActivityFragment extends Fragment  {

    private RecyclerView recyclerView;
    private List<ChoiceListModel> networks;
    private Authenticator authenticator;
    private DatabaseHelper databaseHelper;
    private Button backButton;
    private Button nextButton;
    private Button skipButton;
    private List<NetworkModel> nets;
    private PostModel postModel;

    public NetworkListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_network_list, container, false);
        backButton =(Button)view.findViewById(R.id.backbtn);
        nextButton =(Button)view.findViewById(R.id.nextbtn);
        skipButton =(Button)view.findViewById(R.id.skipbtn);

        recyclerView=(RecyclerView)view.findViewById(R.id.networkview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        networks=new LinkedList<ChoiceListModel>();
        authenticator = Authenticator.getInstance();
        databaseHelper = DatabaseHelper.getInstance();
        postModel=(PostModel) getActivity().getIntent().getSerializableExtra("post");

        databaseHelper.readNetworks(new DataLoadedListener<NetworkModel>() {
            @Override
            public void onDataLoaded(List<NetworkModel> result) {
                nets=result;
                for(int i=0;i<result.size();i++){
                    networks.add(result.get(i).getChoiceListModel());
                }
                RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(networks);
                recyclerView.setAdapter(recyclerChoiceAdapter);
            }
        });

        RecyclerChoiceAdapter recyclerChoiceAdapter = new RecyclerChoiceAdapter(networks);
        recyclerView.setAdapter(recyclerChoiceAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < networks.size(); i++) {
                    NetworkModel networkModel = nets.get(i);
                    networkModel.setChoiceListModel(networks.get(i));
                    if (networkModel.getChoiceListModel().isSelected()) {
                        networkModel.pushPosetId(postModel.getPostId());
                        databaseHelper.writeNetwork(networkModel);
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
        Intent intent=new Intent(getActivity(), UsersListActivity.class);
        intent.putExtra("post", postModel);
        startActivity(intent);
    }
}
