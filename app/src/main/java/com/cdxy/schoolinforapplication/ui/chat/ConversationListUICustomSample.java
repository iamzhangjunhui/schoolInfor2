package com.cdxy.schoolinforapplication.ui.chat;

import android.support.v4.app.Fragment;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;

/**
 * Created by huihui on 2017/2/26.
 */

//继承IMConversationListUI，同时提供构造方法
//自定义一个无标题栏的会话列表的fragment的ui的类
public class ConversationListUICustomSample extends IMConversationListUI {

    public ConversationListUICustomSample(Pointcut pointcut) {
        super(pointcut);
    }

    //不显示标题栏
    @Override
    public boolean needHideTitleView(Fragment fragment) {
        return true;
    }

    @Override
    public boolean getPullToRefreshEnabled() {
        return true;
    }
    @Override
    public boolean enableSearchConversations(Fragment fragment){
        return true;
    }
}