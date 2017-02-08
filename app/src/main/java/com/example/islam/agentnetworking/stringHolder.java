package com.example.islam.agentnetworking;

/**
 * Created by islam on 07/02/17.
 */

public class stringHolder {
    private String name;
    private String id;
    private boolean selected;

    public stringHolder (){
        name=null;
        id=null;
        selected=false;
    }
    public stringHolder(String name, String id,boolean selected) {
        this.name = name;
        this.id = id;
        this.selected=selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }}
