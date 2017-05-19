package com.cdxy.schoolinforapplication.model.topic;

/**
 * Created by huihui on 2017/5/6.
 */

public class ReturnCommentEntity {
    /**
     * commentid : 6
     * topicid : 7777
     * senderNickname : t
     * receiverNickname : a
     * content : dd
     */

    private String commentid;
    private String topicid;
    private String senderNickname;
    private String receiverNickname;
    private String content;

    public ReturnCommentEntity() {
    }

    public ReturnCommentEntity(String topicid, String senderNickname, String receiverNickname, String content) {
        this.topicid = topicid;
        this.senderNickname = senderNickname;
        this.receiverNickname = receiverNickname;
        this.content = content;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
