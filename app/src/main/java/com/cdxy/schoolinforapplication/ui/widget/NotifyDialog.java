package com.cdxy.schoolinforapplication.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/3/9.
 */

public class NotifyDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.dialog_title)
    TextView dialogTitle;
    @BindView(R.id.dialog_cancle)
    Button dialogCancle;
    @BindView(R.id.dialog_ok)
    Button dialogOk;
    private NotifyListener listener;
    private Activity activity;
    private String type;

    public NotifyDialog(@NonNull Context context, @StyleRes int themeResId, NotifyListener notifyListener, Activity activity,String type) {
        super(context, themeResId);
        this.listener = notifyListener;
        this.activity=activity;
        this.type=type;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    public interface NotifyListener {
        void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notify);
        ButterKnife.bind(this);
        //设置dialog的宽度
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.width=activity.getWindowManager().getDefaultDisplay().getWidth()-40;
        getWindow().setAttributes(params);
        //设置dialog显示的位置
        getWindow().setGravity(Gravity.BOTTOM);
        dialogCancle.setOnClickListener(this);
        dialogOk.setOnClickListener(this);
        if (type.equals("delete_my_topic")){
            dialogTitle.setText("删除该话题吗？");
            dialogOk.setText("是的，删除");
            dialogCancle.setText("手贱，按错了");
        }

    }
}
