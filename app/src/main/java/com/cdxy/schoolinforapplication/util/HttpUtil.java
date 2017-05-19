package com.cdxy.schoolinforapplication.util;

import okhttp3.OkHttpClient;

/**
 * Created by huihui on 2017/5/8.
 */

public class HttpUtil { private static OkHttpClient client;
    public static OkHttpClient getClient(){
        synchronized (HttpUtil.class) {
            if (client == null) {
                client = new OkHttpClient();
            }
        }
        return client;
    }
}
