package com.example.islam.agentnetworking;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkListActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<stringHolder> networks;
    public NetworkListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_network_list, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.networkview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        networks=new LinkedList<stringHolder>();
        // trying the view with dummy data
        stringHolder net0=new User("ReMax","3ksdcjsvdbcsk",false);
        stringHolder net1=new User("Max","3ksdcjlksvcsk",false);
        stringHolder net2=new User("Millano","3ksdverlkdbcsk",false);
        stringHolder net3=new User("Rome","3ksdcjlkgbtcsk",false);
        stringHolder net4=new User("Paris","3ksdcjlkdbcsk",false);
        stringHolder net5=new User("Dokky","3ksdcjlkdtbtcsk",false);
        stringHolder net6=new User("Mohandseyn","3ksdcjnhykdbcsk",false);
        stringHolder net7=new User("Zyaed","3ksdcjlkdbcnhk",false);
        stringHolder net8=new User("Cairo","3ksdcjlkdbcuumusk",false);
        stringHolder net9=new User("Giza","3ksdcjlkdbcpl[k",false);
        stringHolder net10=new User("NewYork","3ksdcjlkplbcsk",false);
        stringHolder net11=new User("Delhi","3ksdcjlkdbllsk",false);
        networks.add(net0);
        networks.add(net1);
        networks.add(net2);
        networks.add(net3);
        networks.add(net4);
        networks.add(net5);
        networks.add(net6);
        networks.add(net7);
        networks.add(net8);
        networks.add(net9);
        networks.add(net10);
        networks.add(net11);

        MyAdapter myAdapter=new MyAdapter(networks);
        recyclerView.setAdapter(myAdapter);
        return view;
    }
}
