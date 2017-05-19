package com.cdxy.schoolinforapplication.ui.Message;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.message.MessageEntity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.txt_message_detail_title)
    TextView txtMessageDetailTitle;
    @BindView(R.id.txt_message_detail_content)
    TextView txtMessageDetailContent;
    @BindView(R.id.txt_message_detail_accept_group)
    TextView txtMessageDetailAcceptGroup;
    @BindView(R.id.txt_message_detail_sender)
    TextView txtMessageDetailSender;
    @BindView(R.id.txt_message_detail_send_time)
    TextView txtMessageDetailSendTime;
    @BindView(R.id.activity_message_detail)
    LinearLayout activityMessageDetail;
    private MessageEntity messageEntity;
    private String messageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
        init();
        String title = messageEntity.getTitle();
        if (!TextUtils.isEmpty(title))
            txtMessageDetailTitle.setText(title);
        String content = messageEntity.getContent();
        if (!TextUtils.isEmpty(content))
            txtMessageDetailContent.setText("  " +content);
        String acceptGroup = messageEntity.getSendTo();
        if (!TextUtils.isEmpty(acceptGroup))
            txtMessageDetailAcceptGroup.setText(acceptGroup);
        String sender = messageEntity.getSendPersonName();
        if (!TextUtils.isEmpty(sender))
            txtMessageDetailSender.setText(sender);
        String sendTime = messageEntity.getTime();
        if (!TextUtils.isEmpty(sendTime))
            txtMessageDetailSendTime.setText(sendTime);
        int type=messageEntity.getMessageType();
//        switch (messageType) {
//            case Constant.MY_SEND_MESSAGE:
//                if (type==1) {
//                    btnRight.setText("查看情况");
//                    btnRight.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent=new Intent(MessageDetailActivity.this,SeeMessageStudentsActivity.class);
//                            intent.putExtra("messageEntity",messageEntity);
//                            startActivity(intent);
//                        }
//                    });
//                }
//                break;
//        }
    }

    @Override
    public void init() {
        txtTitle.setText("消息详情");
        Intent intent = getIntent();
        messageEntity = (MessageEntity) intent.getSerializableExtra("message");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                ScreenManager.getScreenManager().popActivty(this);
                break;
        }
    }
}
