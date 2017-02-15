package com.example.islam.agentnetworking.Interactors;

import com.example.islam.agentnetworking.ModelsPackage.PostModel;

/**
 * Created by islam on 15/02/17.
 */

public class AddPostInteractor {
    public boolean validatePostData(PostModel postModel){
        if(postModel.getTitle()!=null&&postModel.getDesc()!=null&&postModel.getUri()!=null&&postModel.getPostId()!=null){
            return true;
        }
        return false;
    }
}
