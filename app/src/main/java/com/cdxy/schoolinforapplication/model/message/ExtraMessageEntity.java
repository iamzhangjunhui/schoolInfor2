package com.cdxy.schoolinforapplication.model.message;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huihui on 2017/4/26.
 */

public class ExtraMessageEntity {
    private String time;
    private String sendname;
    private String[] sendto;
    private int type;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String[] getSendto() {
        return sendto;
    }

    public void setSendto(String[] sendto) {
        this.sendto = sendto;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExtraMessageEntity{" +
                "time='" + time + '\'' +
                ", sendname='" + sendname + '\'' +
                ", sendto=" + Arrays.toString(sendto) +
                ", type=" + type +
                '}';
    }
}
