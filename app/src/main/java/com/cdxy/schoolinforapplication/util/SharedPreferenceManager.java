package com.cdxy.schoolinforapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by huihui on 2017/3/6.
 */

public class SharedPreferenceManager {
    private static final String NAME = "school_infor_db";
    private static SharedPreferenceManager sharedPreferenceManager;
    private Context context;

    public boolean getIsAddtag() {
        return getSharedPreferences().getBoolean("IS_ADD_TAG", false);
    }

    public void setIsAddtag(boolean isAddtag) {
        getEditor().putBoolean("IS_ADD_TAG", isAddtag).commit();
    }
    public String getMyPassword() {
        return getSharedPreferences().getString("MY_PASSWORD", null);
    }

    public void setMyPassword(String password) {
        getEditor().putString("MY_PASSWORD", password).commit();
    }

    public UserInforEntity getUserInfor() {
        String userInforString=getSharedPreferences().getString("USER_INFOR",null);
        Gson gson=new Gson();
        UserInforEntity userInforEntity=gson.fromJson(userInforString,UserInforEntity.class);
        return userInforEntity;
    }

    public void setUserInfor(String userInfor) {
        getEditor().putString("USER_INFOR", userInfor).commit();
    }

    public SharedPreferenceManager(Context context) {
        this.context = context;
    }

    public static SharedPreferenceManager instance(Context context) {
        if (sharedPreferenceManager == null) {
            sharedPreferenceManager = new SharedPreferenceManager(context);
        }
        return sharedPreferenceManager;
    }

    public SharedPreferences.Editor getEditor() {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}
