package com.cdxy.schoolinforapplication.util;

import android.content.Context;
import android.widget.Toast;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetUserInfor {

    public static boolean getMyInfor(final Context context, final String userid) {
        boolean isSucess = false;
        OkHttpClient okHttpClient = HttpUtil.getClient();
        Request request = new Request.Builder().url(HttpUrl.GET_MY_INFOR + "?userid=" + userid).get().build();
        String result = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReturnEntity<UserInforEntity> userInforEntityReturnEntity = SchoolInforManager.gson.fromJson(result, ReturnEntity.class);
        if (userInforEntityReturnEntity != null) {
            userInforEntityReturnEntity = SchoolInforManager.gson.fromJson(result, new TypeToken<ReturnEntity<UserInforEntity>>() {
            }.getType());
            if (userInforEntityReturnEntity.getCode() == 1) {
                String userInforJsonString = SchoolInforManager.gson.toJson(userInforEntityReturnEntity.getData());
                SharedPreferenceManager.instance(context).setUserInfor(userInforJsonString);
                isSucess = true;
            }

        }
        return isSucess;
    }
}
