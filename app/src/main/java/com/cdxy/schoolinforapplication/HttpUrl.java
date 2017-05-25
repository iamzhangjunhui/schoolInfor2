package com.cdxy.schoolinforapplication;

/**
 * Created by huihui on 2017/4/23.
 */

public class HttpUrl {
//    private static final String BASE_URL="http://localhost:8080";
    private static final String BASE_URL="http://120.25.202.192/schoolinfo";
    public static final String SEND_MESSAGE=BASE_URL+"/jpush/fabu";
    public static final String GET_MESSAGE=BASE_URL+"/jpush/getMessage";
    public static final String QUEREN_MESSAGE=BASE_URL+"/jpush/queren";
    public static final String GET_QUERENORNOT=BASE_URL+"/jpush/getQuerenOrNot";
    public static final String SURE_RECEIVE_MESSAGE=BASE_URL+"/Mjshou";
    public static final String MESSAGE_=BASE_URL+"/Mhisget";
    public static final String LOGIN=BASE_URL+"/rest/login";
    public static final String REGISTER=BASE_URL+"/rest/register";
    public static final String GET_MY_INFOR=BASE_URL+"/userInfor/getUserInfor";
    public static final String UPDATE_MY_INFOR=BASE_URL+"/userInfor/updateUserInfor";
    public static final String UPDATE_MY_HEAD_PORTRAIT=BASE_URL+"/userInfor/updatetouxiang";
    public static final String UPDATE_MY_MOTTO=BASE_URL+"/userInfor/updatezuoyouming";
    public static final String ADD_TOPIC=BASE_URL+"/topic/addTopic";
    public static final String ADD_TOPIC_PHOTOS=BASE_URL+"/topic/addPhotos";
    public static final String All_TOPICS=BASE_URL+"/topic/getAllTopic";
    public static final String All_TOPIC_THUMBS=BASE_URL+"/like/getLike";
    public static final String ALL_TOPIC_PHOTOS=BASE_URL+"/topic/getPhotos";
    public static final String ALL_TOPIC_COMMENTS=BASE_URL+"/comment/getAllComment";
    public static final String THUMB=BASE_URL+"/like/addLike";
    public static final String SEND_COMMENT=BASE_URL+"/comment/addComment";
    public static final String UPDATE_PASSWORD=BASE_URL+"/rest/changePassword";
    public static final String DELETE_MY_TOPIC=BASE_URL+"/topic/delTopic";
    public static final String DELETE_MY_COMMENT=BASE_URL+"/comment/delComment";


}

