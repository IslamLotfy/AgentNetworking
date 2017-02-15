package com.example.islam.agentnetworking.Presenters;

import com.example.islam.agentnetworking.CallBackPackage.AddPostListener;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.Interactors.AddPostInteractor;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;

/**
 * Created by islam on 15/02/17.
 */

public class AddPostPresenter {
    private DatabaseHelper databaseHelper;
    private AddPostInteractor interactor;
    public AddPostPresenter(){
        databaseHelper=DatabaseHelper.getInstance();
        interactor=new AddPostInteractor();
    }
    public void AddPost(String title, String desc, String imageUri, String postId, AddPostListener listener){
        PostModel postModel=new PostModel();
        postModel.setTitle(title);
        postModel.setDesc(desc);
        postModel.setUri(imageUri);
        postModel.setPostId(postId);
        if(interactor.validatePostData(postModel)){
            databaseHelper.writePost(postModel);
            listener.onSuccess(postModel);
        } else {
            listener.onFailure();
        }

    }
}
