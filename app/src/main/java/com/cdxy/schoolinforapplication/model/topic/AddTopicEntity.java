package com.cdxy.schoolinforapplication.model.topic;

/**
 * Created by huihui on 2017/5/4.
 */

public class AddTopicEntity {
    private String topicid;//话题的id
    private String authorid;//发起话题人的id
    private String nickName;//昵称
    private String create_time;//发起时间
    private String content;//内容

    public AddTopicEntity(String topicid, String authorid, String nickName, String create_time, String content) {
        this.topicid = topicid;
        this.authorid = authorid;
        this.nickName = nickName;
        this.create_time = create_time;
        this.content = content;
    }

    @Override
    public String toString() {
        return "AddTopicEntity{" +
                "topicid='" + topicid + '\'' +
                ", authorid='" + authorid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", create_time='" + create_time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
