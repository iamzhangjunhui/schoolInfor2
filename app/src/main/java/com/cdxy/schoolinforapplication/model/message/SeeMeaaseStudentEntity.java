package com.cdxy.schoolinforapplication.model.message;

/**
 * Created by huihui on 2016/12/27.
 */

public class SeeMeaaseStudentEntity {
//    private String name;
//    private String id;
//    private String department;
//    private String clazz;
//    private String accept_time;

    private String userid;
    private String xingming;
    private String xibie;
    private String banji;
    private String phone;
    private String queren;
    private String queshi;
    private String xuehao;
    private int TID;//确认的通知ID


    public SeeMeaaseStudentEntity() {

    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return xingming;
    }

    public void setName(String name) {
        this.xingming = name;
    }

    public String getXibie() {
        return xibie;
    }

    public void setXibie(String xibie) {
        this.xibie = xibie;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQueren() {
        return queren;
    }

    public void setQueren(String queren) {
        this.queren = queren;
    }

    public String getQueshi() {
        return queshi;
    }

    public void setQueshi(String queshi) {
        this.queshi = queshi;
    }

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    @Override
    public String toString() {
        return "SeeMeaaseStudentEntity{" +
                "userid='" + userid + '\'' +
                ", name='" + xingming + '\'' +
                ", xibie='" + xibie + '\'' +
                ", banji='" + banji + '\'' +
                ", phone='" + phone + '\'' +
                ", queren='" + queren + '\'' +
                ", queshi='" + queshi + '\'' +
                ", xuehao='" + xuehao + '\'' +
                ", TID='" + TID + '\'' +
                '}';
    }


}
