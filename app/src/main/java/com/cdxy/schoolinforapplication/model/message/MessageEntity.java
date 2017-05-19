package com.cdxy.schoolinforapplication.model.message;

import java.io.Serializable;

/**
 * Created by huihui on 2016/12/27.
 */

public class MessageEntity implements Serializable {

    private long TID;
    private int isSelectAll;//发送时是否选择全选标志
    private String sendPersonName;//发送通知姓名
    private int messageType;//通知类型 1：重要 0 不重要
    private String time;
    //通知发布时间，服务器接收到老师发来的时间
    private String content;//通知内容
    private String title;//通知标题
    private String sendTo;//接收群体

    public MessageEntity() {
    }

    public MessageEntity(int messageType, String sendPersonName, String time, String sendTo,String title, String content) {
        this.messageType = messageType;
        this.sendPersonName = sendPersonName;
        this.time = time;
        this.sendTo = sendTo;
        this.title = title;
        this.content = content;
    }

    public long getTID() {
        return TID;
    }

    public void setTID(long TID) {
        this.TID = TID;
    }

    public int getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(int isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "messageType=" + messageType +
                ", sendPersonName='" + sendPersonName + '\'' +
                ", time='" + time + '\'' +
                ", sendTo='" + sendTo + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
