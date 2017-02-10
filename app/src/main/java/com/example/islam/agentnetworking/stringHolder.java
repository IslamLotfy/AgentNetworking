package com.example.islam.agentnetworking;

/**
 * Created by islam on 07/02/17.
 */

public class stringHolder {
    private String name;
    private boolean selected;

    public stringHolder (){
        name=null;
        selected=false;
    }
    public stringHolder(String name,boolean selected) {
        this.name = name;
        this.selected=selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }}
