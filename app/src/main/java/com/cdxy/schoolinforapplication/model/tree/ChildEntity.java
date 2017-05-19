package com.cdxy.schoolinforapplication.model.tree;

/**
 * Created by huihui on 2017/3/7.
 */

public class ChildEntity {
    private int color;
    private String name;
    private boolean isSelect;

    public ChildEntity(int color, String name) {
        this.color = color;
        this.name = name;
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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
