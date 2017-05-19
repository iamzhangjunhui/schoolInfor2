package com.cdxy.schoolinforapplication.model.topic;

/**
 * Created by huihui on 2017/5/5.
 */

public class ReturnTopicEntity {
    private String topicid;//必有（如果返回的数据topicid为空，则不显示这个话题）
    private String authorid;//必有（保证点击头像或默认头像的时候可以加好友）
    private String nickName;
    private String content;
    private String icon;//如果没有，使用默认头像
    private String createtime;

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "ReturnTopicEntity{" +
                "topicid='" + topicid + '\'' +
                ", authorid='" + authorid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", icon='" + icon + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
