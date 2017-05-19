package com.cdxy.schoolinforapplication.model.topic;

/**
 * Created by huihui on 2017/5/6.
 */

public class ThumbEntity {
    private String topicid;//会话的id
    private String userid;//点赞人的id
    private String nickName;//昵称

    public ThumbEntity(String topicid, String userid, String nickName) {
        this.topicid = topicid;
        this.userid = userid;
        this.nickName = nickName;
    }

    public ThumbEntity(String topicid, String userid) {
        this.topicid = topicid;
        this.userid = userid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "ThumbEntity{" +
                "topicid='" + topicid + '\'' +
                ", userid='" + userid + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
