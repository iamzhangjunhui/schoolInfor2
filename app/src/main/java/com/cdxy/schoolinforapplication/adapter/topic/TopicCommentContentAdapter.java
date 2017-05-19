package com.cdxy.schoolinforapplication.adapter.topic;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.topic.ReturnCommentEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_topic_comment_content, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        ReturnCommentEntity commentContent= (ReturnCommentEntity) getItem(i);
        String content=commentContent.getContent();
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
}
