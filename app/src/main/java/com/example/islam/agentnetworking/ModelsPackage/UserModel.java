package com.example.islam.agentnetworking.ModelsPackage;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 07/02/17.
 */

public class UserModel  implements Serializable{
    private String firstName;
    private String lastName;
    private String networkId;
    private String uId;
    private String email;
    private List<String> PostsId;
    private ChoiceListModel choiceListModel;
    public UserModel(){
        this("","","","");
    }
    public UserModel(String firstName, String lastName , String uid, String network){
        this.firstName=firstName;
        this.lastName=lastName;
        uId=uid;
        this.networkId=network;
        PostsId=new LinkedList<String>();
        choiceListModel=new ChoiceListModel(firstName+" "+lastName,false);
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

    public ChoiceListModel getChoiceListModel() {
        return choiceListModel;
    }

    public void setChoiceListModel(ChoiceListModel choiceListModel) {
        this.choiceListModel = choiceListModel;
    }
}
