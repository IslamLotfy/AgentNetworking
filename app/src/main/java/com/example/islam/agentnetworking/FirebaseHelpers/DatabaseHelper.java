package com.example.islam.agentnetworking.FirebaseHelpers;

import com.example.islam.agentnetworking.CallBackPackage.DataLoadedListener;
import com.example.islam.agentnetworking.ModelsPackage.NetworkModel;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 09/02/17.
 */

public class DatabaseHelper {
    private static DatabaseHelper instance=null;
    private DatabaseReference databaseReference;
    private final String usersNode="users";
    private final String postsNode="posts";
    private final String networksNode="networks";
    private DatabaseHelper(){
        databaseReference=FirebaseDatabase.getInstance().getReference();
    }
    public void writePost(PostModel postModel){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference().child(postsNode).child(postModel.getPostId());
        child.setValue(postModel);
    }
    public void writeUser(UserModel userModel){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference().child(usersNode).child(userModel.getUid());
        child.setValue(userModel);
    }
    public void writeNetwork(NetworkModel net){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference(networksNode).child(net.getuId());
        child.setValue(net);
    }
    public void readPosts(final DataLoadedListener listener){
        final List<PostModel> posts=new LinkedList<PostModel>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child(postsNode);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    PostModel postModel = singleSnapshot.getValue(PostModel.class);
                    posts.add(postModel);
                }
                listener.onDataLoaded(posts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void readUsers(final DataLoadedListener listener){
        final List<UserModel> users=new LinkedList<UserModel>();
        databaseReference=FirebaseDatabase.getInstance().getReference().child(usersNode);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    UserModel userModel = singleSnapshot.getValue(UserModel.class);
                    users.add(userModel);
                }
                listener.onDataLoaded(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void readNetworks(final DataLoadedListener listener){
        final List<NetworkModel> nets=new LinkedList<NetworkModel>();
        databaseReference=FirebaseDatabase.getInstance().getReference().child(networksNode);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    NetworkModel net=ds.getValue(NetworkModel.class);
                    nets.add(net);
                }
                listener.onDataLoaded(nets);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getUser(String uid, final DataLoadedListener listener){
        databaseReference=FirebaseDatabase.getInstance().getReference().child(usersNode).child(uid);
        final List<UserModel> users=new LinkedList<UserModel>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.add(dataSnapshot.getValue(UserModel.class));
                listener.onDataLoaded(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public static DatabaseHelper getInstance(){
        if(instance==null){
            instance=new DatabaseHelper();
            return instance;
        }else
            return instance;
    }
}
