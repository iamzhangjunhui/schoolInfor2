package com.cdxy.schoolinforapplication.model;

import com.cdxy.schoolinforapplication.model.message.SeeMeaaseStudentEntity;

import java.util.ArrayList;

/**
 * Created by huihui on 2017/4/26.
 */

public class QuerenOrNotReturnEntity<T> {
    private int code;
    private String msg;
    private ArrayList<SeeMeaaseStudentEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<SeeMeaaseStudentEntity> getData() {
        return data;
}

    public void setData(ArrayList<SeeMeaaseStudentEntity> data) {
        this.data = data;
    }
}
