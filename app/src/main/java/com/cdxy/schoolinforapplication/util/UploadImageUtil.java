package com.cdxy.schoolinforapplication.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huihui on 2017/4/23.
 * 使用Okhttp实现图片上传
 */

public class UploadImageUtil {
    public void uploadImage(List<String> paths) {
        OkHttpClient okHttpClient =  HttpUtil.getClient();
        MediaType mediaType = MediaType.parse("image/png");
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < paths.size(); i++) {
            File file = new File(paths.get(i));
            if (file != null) {
                builder.addFormDataPart("img", file.getName(), MultipartBody.create(mediaType, file));
            }
        }
        MultipartBody multipartBody = builder.build();
        Request request = new Request.Builder().url("").post(multipartBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

}
