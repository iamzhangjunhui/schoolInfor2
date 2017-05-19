package com.cdxy.schoolinforapplication.ui.chat;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactOperateNotifyListener;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.contact.IYWDBContact;
import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.ui.MainActivity;
import com.cdxy.schoolinforapplication.ui.base.BaseFragment;
import com.cdxy.schoolinforapplication.ui.load.LoginActivity;
import com.cdxy.schoolinforapplication.ui.widget.EdtDialog;
import com.cdxy.schoolinforapplication.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment {
    private EdtDialog edtDialog;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        Fragment f = LoginActivity.ywimKit.getConversationFragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.chat_conversation_fragment, f).commit();
    }

    @Override
    public void init() {
        IYWContactOperateNotifyListener mContactOperateNotifyListener = new IYWContactOperateNotifyListener() {

            /**
             * 用户请求加你为好友
             *  该回调在UI线程回调 ，请勿做太重的操作
             *
             * @param contact 用户的信息
             * @param message 附带的备注
             */
            @Override
            public void onVerifyAddRequest(final IYWContact contact, String message) {
                toast(contact.getShowName() + "请求加你为好友");
                edtDialog = new EdtDialog(getContext(), R.style.MyDialog, new EdtDialog.AddFriendListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.btn_sure:
                                addFriend(contact.getUserId());
                                edtDialog.dismiss();
                                break;
                            case R.id.btn_cancel:
                                edtDialog.dismiss();
                                break;
                        }
                    }
                }, getActivity(), Constant.EDTDIALOG_TYPE_ADD_FRIEND);
                edtDialog.show();
            }

            /**
             * 用户接受了你的好友请求
             *  该回调在UI线程回调 ，请勿做太重的操作
             *
             * @param contact 用户的信息
             */
            @Override
            public void onAcceptVerifyRequest(IYWContact contact) {
                toastLongShow(contact.getShowName() + "接受了你的好友请求");
            }

            @Override
            public void onDenyVerifyRequest(IYWContact iywContact) {

            }

            /**
             * 云旺服务端（或其它终端）进行了好友添加操作
             *  该回调在UI线程回调 ，请勿做太重的操作
             *
             * @param contact 用户的信息
             */
            @Override
            public void onSyncAddOKNotify(IYWContact contact) {
            }

            /**
             * 用户从好友名单删除了您
             *  该回调在UI线程回调 ，请勿做太重的操作
             *
             * @param contact 用户的信息
             */
            @Override
            public void onDeleteOKNotify(IYWContact contact) {
                toast(contact.getShowName() + "将你从他的好友名单中删除");
            }

            @Override
            public void onNotifyAddOK(IYWContact iywContact) {

            }
        };
        LoginActivity.iywContactService.addContactOperateNotifyListener(mContactOperateNotifyListener);
    }

    private  void addFriend(String target) {
        IWxCallback callback = new IWxCallback() {

            @Override
            public void onSuccess(Object... result) {
                Toast.makeText(getContext(), "添加好友成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onError(int code, String info) {

            }
        };
        LoginActivity.iywContactService.ackAddContact(target, SchoolInforManager.appKay, true, edtDialog.content, callback);

    }
}
