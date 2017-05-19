package com.cdxy.schoolinforapplication.model.chat;

/**
 * Created by huihui on 2017/2/26.
 */

    public class MyFriendEntity {
    private String name;
    private String icon;
    private boolean isOnline;
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
