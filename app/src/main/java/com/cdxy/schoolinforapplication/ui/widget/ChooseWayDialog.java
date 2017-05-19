package com.cdxy.schoolinforapplication.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/3/23.
 */

public class ChooseWayDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.txt_way1)
    TextView txtWay1;
    @BindView(R.id.txt_way2)
    TextView txtWay2;
    private Activity activity;
    private ChooseWayDialogListener chooseWayDialogListener;
    private String type;

    public ChooseWayDialog(@NonNull Context context, @StyleRes int themeResId, ChooseWayDialogListener chooseWayDialogListener, Activity activity,String type) {
        super(context, themeResId);
        this.activity = activity;
        this.chooseWayDialogListener = chooseWayDialogListener;
        this.type=type;
    }

    @Override
    public void onClick(View view) {
        chooseWayDialogListener.onClick(view);
    }

    public interface ChooseWayDialogListener {
        public void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_way);
        ButterKnife.bind(this);
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.width=activity.getWindowManager().getDefaultDisplay().getWidth()-40;
        getWindow().setAttributes(params);
        txtWay1.setOnClickListener(this);
        txtWay2.setOnClickListener(this);
        if (type== Constant.CHOOSE_WAY_DIALOG_TYPE_GET_PHOTO){
            txtWay1.setText("拍照");
            txtWay2.setText("从相册中获取");
        }else {
            txtWay1.setText("打电话");
            txtWay2.setText("发短信");
        }

    }
}
