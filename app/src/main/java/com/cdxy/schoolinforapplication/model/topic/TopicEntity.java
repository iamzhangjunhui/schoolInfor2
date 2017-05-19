package com.cdxy.schoolinforapplication.model.topic;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by huihui on 2016/12/30.
 */

public class TopicEntity {
    private String topicid;//话题id;
    private String userid;//发起话题人的id
    private Object icon;//头像
    private String nickName;//昵称
    private String create_time;//发起时间
    private String content;//内容
    private List<String> thumbPersonsNickname;//点赞人姓名
    private List<Object> photos;
    private List<ReturnCommentEntity> comments;
    private boolean iHasThumb;
    public TopicEntity() {
    }

    public List<Object> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Object> photos) {
        this.photos = photos;
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

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getThumbPersonsNickname() {
        return thumbPersonsNickname;
    }

    public void setThumbPersonsNickname(List<String> thumbPersonsNickname) {
        this.thumbPersonsNickname = thumbPersonsNickname;
    }

    public boolean isiHasThumb() {
        return iHasThumb;
    }

    public void setiHasThumb(boolean iHasThumb) {
        this.iHasThumb = iHasThumb;
    }

    public List<ReturnCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<ReturnCommentEntity> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
                "topicid='" + topicid + '\'' +
                ", userid='" + userid + '\'' +
                ", icon=" + icon +
                ", nickName='" + nickName + '\'' +
                ", create_time='" + create_time + '\'' +
                ", content='" + content + '\'' +
                ", thumbPersonsNickname=" + thumbPersonsNickname +
                ", photos=" + photos +
                ", comments=" + comments +
                ", iHasThumb=" + iHasThumb +
                '}';
    }
}
