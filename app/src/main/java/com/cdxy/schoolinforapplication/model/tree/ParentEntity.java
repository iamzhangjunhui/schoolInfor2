package com.cdxy.schoolinforapplication.model.tree;

import java.util.List;

/**
 * Created by huihui on 2017/3/7.
 */

public class ParentEntity {
    private int color;
    private boolean isSelect;
    private String name;
    private List<ChildEntity> children;
    private boolean isExpand;

    public ParentEntity(int color, String name, List<ChildEntity> children) {
        this.color = color;
        this.name = name;
        this.children = children;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
