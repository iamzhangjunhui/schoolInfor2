package com.cdxy.schoolinforapplication.model;

import java.util.List;

/**
 * Created by huihui on 2017/4/23.
 */

public class SendMessageEntity {
    private String title;
    private String content;
    private int messageType;
    private List<String> sendTolist;
    private String sendPersonName;
    private int isSelectAll;

    public SendMessageEntity() {
    }

    public SendMessageEntity(String title, String content, int messageType, List<String> sendTo, String sendPersonName, int isSelectAll) {
        this.title = title;
        this.content = content;
        this.messageType = messageType;
        this.sendTolist = sendTo;
        this.sendPersonName = sendPersonName;
        this.isSelectAll = isSelectAll;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public List<String> getSendTo() {
        return sendTolist;
    }

    public void setSendTo(List<String> sendTo) {
        this.sendTolist = sendTo;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public int getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(int isSelectAll) {
        this.isSelectAll = isSelectAll;
    }
}
