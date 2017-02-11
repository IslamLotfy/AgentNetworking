package com.example.islam.agentnetworking.ModelsPackage;

import java.io.Serializable;

/**
 * Created by islam on 07/02/17.
 */

public class PostModel implements Serializable {
    private String uri;
    private String title;
    private String desc;
    private String postId;

    public PostModel(){
        this("","","","");
    }

    public PostModel(String uri, String title, String desc, String postId) {
        this.uri = uri;
        this.title = title;
        this.desc = desc;
        this.postId=postId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
