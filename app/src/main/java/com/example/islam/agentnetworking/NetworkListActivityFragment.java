package com.example.islam.agentnetworking;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkListActivityFragment extends Fragment implements Databaselistener,authenticationListener {

    private RecyclerView recyclerView;
    private List<stringHolder> networks;
    private List<stringHolder> users;
    private Authentication auth;
    private Databasehelper databasehelper;
    private Button backbtn;
    private Button nextbtn;
    private Button skipbtn;
    private boolean b;
    private boolean end;
    private User user;
    private List<Network> nets;
    private List<User> userList;
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
        Bundle args=getArguments();
        b=false;
        end=false;
        user=databasehelper.getUser(auth.getUserId());

        if(args!=null) {
            b=args.getBoolean("type");
            userList=databasehelper.readUsers();
            for(int i=0;i<userList.size();i++){
                if(userList.get(i).getNetwork().equals(user.getNetwork()))
                users.add(userList.get(i));
            }
            MyAdapter myAdapter = new MyAdapter(users);
            recyclerView.setAdapter(myAdapter);
        }else{
            nets=databasehelper.readNetworks();
            for(int i=0;i<nets.size();i++){
                networks.add(nets.get(i));
            }
            MyAdapter myAdapter = new MyAdapter(networks);
            recyclerView.setAdapter(myAdapter);
        }


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext()," and   dbdbdb",Toast.LENGTH_LONG).show();
                if(!b &&!end){
                    for (int i = 0; i < networks.size(); i++) {
                        Network net = (Network) networks.get(i);
                        if (net.isSelected()) {
                            net.pushPosetId("postid");
                            databasehelper.writeNetwork(net);
                        }
                    }
                    NetworkListActivityFragment fragment = new NetworkListActivityFragment();
                    Bundle args = new Bundle();
                    args.putBoolean("type", true);
                    fragment.setArguments(args);
                    fragmentreplacelistener fc = (fragmentreplacelistener) getActivity();
                    fc.onFragmentReplaceListener(fragment);
                }else if(b&&!end){
                    databasehelper.readUsers();
                    end=true;
                    //post ended and write it
                }else{

                }
            }
        });
        return view;
    }
    @Override
    public void onDatabaseSuccess(List<stringHolder> list) {
//         if(!b) {
//             networks = list;
//             MyAdapter myAdapter = new MyAdapter(networks);
//             recyclerView.setAdapter(myAdapter);
//         }else{
//             users=new LinkedList<stringHolder>();
//             for(int i=0;i<list.size();i++){
//                 User u=(User)list.get(i);
//                 if(user.getNetwork().equals(u.getNetwork())){
//                     users.add(u);
//                 }
//             }
//             MyAdapter myAdapter = new MyAdapter(users);
//             recyclerView.setAdapter(myAdapter);
//         }
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
