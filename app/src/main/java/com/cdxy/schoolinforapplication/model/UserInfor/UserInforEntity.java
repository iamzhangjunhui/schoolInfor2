package com.cdxy.schoolinforapplication.model.UserInfor;

import java.io.Serializable;

/**
 * Created by huihui on 2017/3/6.
 */

public class UserInforEntity implements Serializable {
    private String userid="";
    private String mima="";
    private String nicheng="";
    private String xingming="";
    private String xibie="";
    private String banji="";
    private String xuehao="";
    private String xingbie="";
    private String shengri="";
    private String minzu="";
    private String jia="";
    private String xingqu="";
    private String shenfen="";
    private String touxiang="";
    private String zuoyouming="";

    public UserInforEntity() {
    }
    public UserInforEntity(String userid, String nicheng, String xingming, String xibie, String banji, String xuehao, String xingbie, String shengri, String minzu, String jia, String xingqu, String shenfen) {
        this.userid = userid;
        this.nicheng = nicheng;
        this.xingming = xingming;
        this.xibie = xibie;
        this.banji = banji;
        this.xuehao = xuehao;
        this.xingbie = xingbie;
        this.shengri = shengri;
        this.minzu = minzu;
        this.jia = jia;
        this.xingqu = xingqu;
        this.shenfen = shenfen;
    }

    public UserInforEntity(String userid, String nicheng, String xingming, String xibie, String banji, String xuehao, String xingbie, String shengri, String minzu, String jia, String xingqu) {
        this.userid = userid;
        this.nicheng = nicheng;
        this.xingming = xingming;
        this.xibie = xibie;
        this.banji = banji;
        this.xuehao = xuehao;
        this.xingbie = xingbie;
        this.shengri = shengri;
        this.minzu = minzu;
        this.jia = jia;
        this.xingqu = xingqu;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
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

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getShengri() {
        return shengri;
    }

    public void setShengri(String shengri) {
        this.shengri = shengri;
    }

    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }

    public String getJia() {
        return jia;
    }

    public void setJia(String jia) {
        this.jia = jia;
    }

    public String getXingqu() {
        return xingqu;
    }

    public void setXingqu(String xingqu) {
        this.xingqu = xingqu;
    }

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getZuoyouming() {
        return zuoyouming;
    }

    public void setZuoyouming(String zuoyouming) {
        this.zuoyouming = zuoyouming;
    }

    @Override
    public String toString() {
        return "UserInforEntity{" +
                "userid='" + userid + '\'' +
                ", mima='" + mima + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", xingming='" + xingming + '\'' +
                ", xibie='" + xibie + '\'' +
                ", banji='" + banji + '\'' +
                ", xuehao='" + xuehao + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", shengri='" + shengri + '\'' +
                ", minzu='" + minzu + '\'' +
                ", jia='" + jia + '\'' +
                ", xingqu='" + xingqu + '\'' +
                ", shenfen='" + shenfen + '\'' +
                ", touxiang='" + touxiang + '\'' +
                ", zuoyouming='" + zuoyouming + '\'' +
                '}';
    }
}
