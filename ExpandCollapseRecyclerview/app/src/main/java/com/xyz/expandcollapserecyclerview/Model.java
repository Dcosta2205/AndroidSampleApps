package com.xyz.expandcollapserecyclerview;

import java.util.List;

public class Model {

    private String familyName;

    private List<String> familyMemberNames;
    private boolean isExpanded;
    private int pos;


    public Model(String familyName, List<String> familyMemberNames, boolean isExpanded, int pos) {
        this.familyName = familyName;
        this.familyMemberNames = familyMemberNames;
        this.isExpanded= isExpanded;
        this.pos = pos;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<String> getFamilyMemberNames() {
        return familyMemberNames;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public int getPos() {
        return pos;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
