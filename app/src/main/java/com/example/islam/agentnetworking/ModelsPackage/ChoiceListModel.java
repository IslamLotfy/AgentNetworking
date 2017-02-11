package com.example.islam.agentnetworking.ModelsPackage;

/**
 * Created by islam on 11/02/17.
 */

public class ChoiceListModel {
    private String name;
    private boolean selected;

    public ChoiceListModel(){
        this("",false);
    }

    public ChoiceListModel(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
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
    }
}
