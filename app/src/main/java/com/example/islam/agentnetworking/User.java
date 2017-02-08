package com.example.islam.agentnetworking;

import android.provider.Contacts;

/**
 * Created by islam on 07/02/17.
 */

public class User extends stringHolder{
    private String firstName;
    private String lastName;
    private String network;
    private String uId;
    private String email;
    private String pass;
    public User(String name, String s, boolean b){
        super(name,s,b);
        firstName="";
        lastName="";
        network="";
        uId="";
    }
    public User(String firstName,String lastName ,String uid,String network){
        super(firstName+lastName,uid,false);
        this.firstName=firstName;
        this.lastName=lastName;
        uId=uid;
        this.network=network;
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
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
