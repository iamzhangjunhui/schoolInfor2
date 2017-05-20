package com.cdxy.schoolinforapplication.util;

import android.content.Context;
import android.widget.Toast;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class GetUserInfor {

    public static void getMyInfor(final Context context, final String userid) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = HttpUtil.getClient();
                Request request = new Request.Builder().url(HttpUrl.GET_MY_INFOR + "?userid=" + userid).get().build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ReturnEntity<UserInforEntity> userInforEntityReturnEntity = SchoolInforManager.gson.fromJson(s, ReturnEntity.class);
                if (userInforEntityReturnEntity != null) {
                    userInforEntityReturnEntity = SchoolInforManager.gson.fromJson(s, new TypeToken<ReturnEntity<UserInforEntity>>() {
                    }.getType());
                    if (userInforEntityReturnEntity.getCode() == 1) {
                        String userInforJsonString = SchoolInforManager.gson.toJson(userInforEntityReturnEntity.getData());
                        SharedPreferenceManager.instance(context).setUserInfor(userInforJsonString);
                    } else {
                        Toast.makeText(context, userInforEntityReturnEntity.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "获取个人信息失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
