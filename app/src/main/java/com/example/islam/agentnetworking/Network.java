package com.example.islam.agentnetworking;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 10/02/17.
 */

public class Network extends stringHolder {

    private String uId;
    private String name;
    private List<String> UsersId;
    private List<String> PostsId;
    public Network(String name,boolean b){
        this(name,b,"");
    }
    public Network(String name,boolean b,String uId){
        super(name,b);
        this.name=name;
        this.uId=uId;
        UsersId =new LinkedList<String>();

    }
    public Network(){
       this("",false,"");
    }
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public List<String> getUsersId() {
        return UsersId;
    }

    public void setUsersId(List<String> usersId) {
        this.UsersId = usersId;
    }

    public List<String> getPostsId() {
        return PostsId;
    }

    public void setPostsId(List<String> postsId) {
        this.PostsId = postsId;
    }

    public void pushUserId(String userid){
        UsersId.add(userid);
    }

    public void pushPosetId(String postid){
        PostsId.add(postid);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
