package com.cdxy.schoolinforapplication.adapter.topic;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.model.topic.ReturnCommentEntity;
import com.cdxy.schoolinforapplication.model.topic.TopicEntity;
import com.cdxy.schoolinforapplication.util.HttpUtil;
import com.cdxy.schoolinforapplication.util.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by huihui on 2017/1/1.
 */

public class TopicCommentContentAdapter extends BaseAdapter {
    private Context context;
    private List<ReturnCommentEntity> list;

    public TopicCommentContentAdapter(Context context, List<ReturnCommentEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_topic_comment_content, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final ReturnCommentEntity commentContent= (ReturnCommentEntity) getItem(i);
        final String content=commentContent.getContent();
        if (!TextUtils.isEmpty(content)){
            viewHolder.commentContent.setVisibility(View.VISIBLE);
            //显示在屏幕上的发送者名称
            String senderNickName=commentContent.getSenderNickname();
            String receiverNickName=commentContent.getReceiverNickname();
            //包含发送人和接收者和评价内容的信息
            String commentContentString;
            if (TextUtils.isEmpty(receiverNickName)||receiverNickName.equals(senderNickName)) {
                commentContentString="<font color='blue'>"+senderNickName+":"+"</font>"+content;
            }else {
                commentContentString="<font color='blue'>"+senderNickName+"</font>"+" 回复 "+"<font color='blue'>"+receiverNickName+":"+"</font>"+content;
            }
            viewHolder.commentContent.setText(Html.fromHtml(commentContentString));
            viewHolder.commentContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserInforEntity userInfor= SharedPreferenceManager.instance(context).getUserInfor();
                    if (userInfor!=null){
                        String myNickname=userInfor.getNicheng();
                        if (myNickname.equals(commentContent.getSenderNickname())){
                            deleteMyComment(i,commentContent);
                        }
                    }
                }
            });
        }else {
            viewHolder.commentContent.setVisibility(View.GONE);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.comment_content)
        TextView commentContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private void deleteMyComment( final int position , final ReturnCommentEntity entity) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient= HttpUtil.getClient();
                Request request=new Request.Builder().url(HttpUrl.DELETE_MY_COMMENT+"?commentid="+entity.getCommentid()+"&&topicid="+entity.getTopicid()).get().build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ReturnEntity returnEntity= SchoolInforManager.gson.fromJson(s,ReturnEntity.class);
                if (returnEntity!=null) {
                    if (returnEntity.getCode() == 1) {
                        list.remove(position);
                        TopicCommentContentAdapter.this.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context,returnEntity.getMsg()+"",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}
