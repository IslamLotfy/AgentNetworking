package com.example.islam.agentnetworking.CallBackPackage;

import com.example.islam.agentnetworking.ModelsPackage.PostModel;

/**
 * Created by islam on 15/02/17.
 */

public interface AddPostListener {
    public void onSuccess(PostModel postModel);
    public void onFailure();
}
