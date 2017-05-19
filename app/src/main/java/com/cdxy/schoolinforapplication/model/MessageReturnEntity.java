package com.cdxy.schoolinforapplication.model;

import com.cdxy.schoolinforapplication.model.message.MessageEntity;

import java.util.ArrayList;

/**
 * Created by huihui on 2017/4/26.
 */

public class MessageReturnEntity<T> {
    private int code;
    private String msg;
    private ArrayList<MessageEntity> data;

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

    public ArrayList<MessageEntity> getData() {
        return data;
}

    public void setData(ArrayList<MessageEntity> data) {
        this.data = data;
    }
}
