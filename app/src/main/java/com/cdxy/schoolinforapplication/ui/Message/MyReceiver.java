package com.cdxy.schoolinforapplication.ui.Message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.model.message.MessageEntity;
import com.cdxy.schoolinforapplication.util.HttpUtil;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 极光推送消息处理类
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private Handler handler;
    private String senderString;
    private String sender;
    private String sendTimeString;
    private String sendTime;
    private String acceptGroupString;
    private String acceptGroup;
    private String messageTypeString;
    private String messageType;
    private String messageId;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //获得接受消息设备注册ID

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            sureReceiveMessage();
            //打开自定义的Activity
            Intent i = new Intent(context, MessageDetailActivity.class);
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setTitle(bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
            messageEntity.setContent(bundle.getString(JPushInterface.EXTRA_ALERT));
           //获取并解析附加信息
            String extreMessage = bundle.getString(JPushInterface.EXTRA_EXTRA);
//           Gson gson=new Gson();
//            ExtraMessageEntity extraMessageEntity=gson.fromJson(extreMessage,ExtraMessageEntity.class);
            String[] values = extreMessage.split(",");
            String value1_key = (values[0].split(":"))[0];
            String value1 = (values[0].split(":"))[1];
            if (value1_key.contains("发送人")) {
                senderString = value1;
            } else if (value1_key.contains("时间")) {
                sendTimeString = value1;
            } else if (value1_key.contains("接收群体")) {
                acceptGroupString = value1;
            } else if (value1_key.contains("类型")) {
                messageTypeString = value1;
            }
            String value2_key = (values[1].split(":"))[0];
            String value2 = (values[1].split(":"))[1];
            if (value2_key.contains("发送人")) {
                senderString = value2;
            } else if (value2_key.contains("时间")) {
                sendTimeString = value2;
            } else if (value2_key.contains("接收群体")) {
                acceptGroupString = value2;
            } else if (value2_key.contains("类型")) {
                messageTypeString = value2;
            }
            String value3_key = (values[2].split(":"))[0];
            String value3 = (values[2].split(":"))[1];
            if (value1_key.contains("发送人")) {
                senderString = value3;
            } else if (value3_key.contains("时间")) {
                sendTimeString = value3;
            } else if (value3_key.contains("接收群体")) {
                acceptGroupString = value3;
            } else if (value3_key.contains("类型")) {
                messageTypeString = value3;
            }
            String value4_key = (values[3].split(":"))[0];
            String value4 = (values[3].split(":"))[1];
            if (value4_key.contains("发送人")) {
                senderString = value4;
            } else if (value4_key.contains("发送时间")) {
                sendTimeString = value4;
            } else if (value4_key.contains("接收群体")) {
                acceptGroupString = value4;
            } else if (value4_key.contains("类型")) {
                messageTypeString = value4;
            }
            sender = senderString.substring(1, senderString.length() - 1);
            sendTime = sendTimeString.substring(1, sendTimeString.length() - 1);
            acceptGroup = acceptGroupString.substring(1, acceptGroupString.length() - 1);
            messageType = messageTypeString.substring(1, messageTypeString.indexOf("}") - 1);
            messageEntity.setTime(sendTime);
            messageEntity.setSendPersonName(sender);
            messageEntity.setSendTo(acceptGroup);
            messageEntity.setMessageType(Integer.parseInt(messageType));
            i.putExtra("message", messageEntity);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
    }
    private void sureReceiveMessage(){
        OkHttpClient okHttpClient= HttpUtil.getClient();
        Request request=new Request.Builder().url(HttpUrl.SURE_RECEIVE_MESSAGE).get().build();
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
