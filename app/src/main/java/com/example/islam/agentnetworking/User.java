package com.example.islam.agentnetworking;

import android.provider.Contacts;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 07/02/17.
 */

public class User extends stringHolder implements Serializable{
    private String firstName;
    private String lastName;
    private String networkId;
    private String uId;
    private String email;
    private List<String> PostsId;
    public User(){
        this("","","","");
    }
    public User(String firstName,String lastName ,String uid,String network){
        super(firstName+lastName,false);
        this.firstName=firstName;
        this.lastName=lastName;
        uId=uid;
        this.networkId=network;
        PostsId=new LinkedList<String>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUid() {
        return uId;
    }

    public void setUid(String uid) {
        uId = uid;
    }

    public String getNetwork() {
        return networkId;
    }

    public void setNetwork(String network) {
        this.networkId = network;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPostsId() {
        return PostsId;
    }
    public void setPostsId(List<String> postsId) {
        PostsId = postsId;
    }
    public void pushPostId(String postId){
        PostsId.add(postId);
    }
}
