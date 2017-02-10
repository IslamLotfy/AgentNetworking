package com.example.islam.agentnetworking;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkListActivityFragment extends Fragment implements Databaselistener,authenticationListener {

    private RecyclerView recyclerView;
    private List<stringHolder> networks;
    private Authentication auth;
    private Databasehelper databasehelper;
    private Button backbtn;
    private Button nextbtn;
    private Button skipbtn;
    public NetworkListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_network_list, container, false);
        backbtn=(Button)view.findViewById(R.id.backbtn);
        nextbtn=(Button)view.findViewById(R.id.nextbtn);
        skipbtn=(Button)view.findViewById(R.id.skipbtn);

        recyclerView=(RecyclerView)view.findViewById(R.id.networkview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        networks=new LinkedList<stringHolder>();
        auth=Authentication.getInstance(this);
        databasehelper=Databasehelper.getInstance(this);

        databasehelper.readNetworks();

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0 ;i<networks.size();i++){
                    Network net= (Network) networks.get(i);
                    if(net.isSelected()){
                        net.pushPosetId("");
                        databasehelper.writeNetwork(net);
                    }
                }
            }
        });
        return view;
    }
    @Override
    public void onDatabaseSuccess(List<stringHolder> list) {
            networks = list;
            MyAdapter myAdapter = new MyAdapter(networks);
            recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onDatabaseFailure() {

    }

    @Override
    public void onAuthSuccess() {

    }

    @Override
    public void onAuthFailure() {

    }
}
