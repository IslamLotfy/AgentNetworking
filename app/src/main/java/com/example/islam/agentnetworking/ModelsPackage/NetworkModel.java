package com.example.islam.agentnetworking.ModelsPackage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 10/02/17.
 */

public class NetworkModel  {

    private String uId;
    private String name;
    private List<String> UsersId;
    private List<String> PostsId;
    private ChoiceListModel choiceListModel;
    public NetworkModel(String name, boolean b){
        this(name,b,"");
    }
    public NetworkModel(String name, boolean b, String uId){
        this.name=name;
        this.uId=uId;
        UsersId =new LinkedList<String>();
        PostsId=new LinkedList<String >();
        choiceListModel=new ChoiceListModel(name,false);
    }
    public NetworkModel(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChoiceListModel getChoiceListModel() {
        return choiceListModel;
    }

    public void setChoiceListModel(ChoiceListModel choiceListModel) {
        this.choiceListModel = choiceListModel;
    }
}
