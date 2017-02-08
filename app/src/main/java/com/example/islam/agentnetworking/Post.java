package com.example.islam.agentnetworking;

import android.net.Uri;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by islam on 07/02/17.
 */

public class Post {
    private Uri uri;
    private String title;
    private String desc;
    private List<String> agents;
    private List<String> networks;

    public Post(){
        uri=null;
        title=null;
        desc=null;
        agents=new LinkedList<String>();
        networks=new LinkedList<String>();
    }

    public Post(Uri uri, String title, String desc, List<String> agents, List<String> networks) {
        this.uri = uri;
        this.title = title;
        this.desc = desc;
        this.agents = agents;
        this.networks = networks;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
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

    public List<String> getAgents() {
        return agents;
    }

    public void setAgents(List<String> agents) {
        this.agents = agents;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }
}
