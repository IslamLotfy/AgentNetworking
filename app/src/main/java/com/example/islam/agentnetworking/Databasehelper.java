package com.example.islam.agentnetworking;

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

public class Databasehelper  {
    private static Databasehelper instance=null;
    private DatabaseReference databaseReference;
    private Databaselistener listener;

    private Databasehelper(Databaselistener listener){
        databaseReference=FirebaseDatabase.getInstance().getReference();
        this.listener=listener;
    }
    public void writePost(Post post){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference("posts").child(post.getPostId());
        child.setValue(post);
    }
    public void writeUser(User user){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        child.setValue(user);
    }
    public void writeNetwork(Network net){
        DatabaseReference child=FirebaseDatabase.getInstance().getReference("networks").child(net.getuId());
        child.setValue(net);
    }
    public List<Post> readPosts(){
        final List<Post> posts=new LinkedList<Post>();
        databaseReference= FirebaseDatabase.getInstance().getReference("posts");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Post post = singleSnapshot.getValue(Post.class);
                    posts.add(post);
                }
                //listener.onDatabaseSuccess(posts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return posts;
    }
    public List<User> readUsers(){
        final List<User> users=new LinkedList<User>();
        databaseReference=FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    User user = singleSnapshot.getValue(User.class);
                    users.add(user);
                }
                //listener.onDatabaseSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return users;
    }
    public List<Network> readNetworks(){
        final List<Network> nets=new LinkedList<Network>();
        databaseReference=FirebaseDatabase.getInstance().getReference("networks");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Network net=ds.getValue(Network.class);
                    nets.add(net);
                }
                //listener.onDatabaseSuccess(nets);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return nets;
    }
    public User getUser(String uid){
        databaseReference=FirebaseDatabase.getInstance().getReference("users").child(uid);
        final User[] user = new User[1];
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user[0] =dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return user[0];
    }
    public static Databasehelper getInstance(Databaselistener listener){
        if(instance==null){
            instance=new Databasehelper(listener);
            return instance;
        }else
            return instance;
    }
}
