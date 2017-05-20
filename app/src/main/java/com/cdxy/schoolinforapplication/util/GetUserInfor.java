package com.cdxy.schoolinforapplication.util;

import android.content.Context;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;



public class GetUserInfor {

    public static void getMyInfor(final Context context, String userid) {
        OkHttpClient okHttpClient = HttpUtil.getClient();
        final Gson gson = new Gson();
        Request request = new Request.Builder().url(HttpUrl.GET_MY_INFOR + "?userid=" + userid).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Observable.just(response.body().string())
                        .map(new Func1<String, ReturnEntity<UserInforEntity>>() {
                            @Override
                            public ReturnEntity<UserInforEntity> call(String s) {
                                ReturnEntity<UserInforEntity> userInforEntityReturnEntity = gson.fromJson(s, ReturnEntity.class);
                                if (userInforEntityReturnEntity != null) {
                                    userInforEntityReturnEntity = gson.fromJson(s, new TypeToken<ReturnEntity<UserInforEntity>>() {
                                    }.getType());
                                    return userInforEntityReturnEntity;
                                }
                                return null;
                            }
                        }).subscribeOn(Schedulers.newThread())
                        .subscribe(new Action1<ReturnEntity<UserInforEntity>>() {
                            @Override
                            public void call(ReturnEntity<UserInforEntity> userInforEntityReturnEntity) {
                                if (userInforEntityReturnEntity.getCode() == 1) {
                                    //如果个人信息获取成功
                                    String userInforJsonString = gson.toJson(userInforEntityReturnEntity.getData());
                                    SharedPreferenceManager.instance(context).setUserInfor(userInforJsonString);
                                }
                            }
                        });
            }
        });
    }
}
