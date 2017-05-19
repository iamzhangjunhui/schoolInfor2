package com.cdxy.schoolinforapplication.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/3/15.
 */

public class EdtDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.edt_content)
    EditText edtContent;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    private AddFriendListener addFriendListener;
    public static String content = "";
    private Activity activity;
    private String type;
    private String authorid;

    public EdtDialog(@NonNull Context context, @StyleRes int themeResId, AddFriendListener addFriendListener, Activity activity, String type) {
        super(context, themeResId);
        this.addFriendListener = addFriendListener;
        this.activity = activity;
        this.type = type;
    }
    public EdtDialog(@NonNull Context context, @StyleRes int themeResId, AddFriendListener addFriendListener, Activity activity, String type,String authorid) {
        super(context, themeResId);
        this.addFriendListener = addFriendListener;
        this.activity = activity;
        this.type = type;
        this.authorid=authorid;
    }

    @Override
    public void onClick(View view) {
        content = edtContent.getText().toString();
        addFriendListener.onClick(view);
    }

    public interface AddFriendListener {
        void onClick(View view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_friend);
        ButterKnife.bind(this);
        //设置dialog显示的宽度
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = activity.getWindowManager().getDefaultDisplay().getWidth() - 40;
        getWindow().setAttributes(params);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if (type.equals(Constant.EDTDIALOG_TYPE_ADD_FRIEND)){
            txtTitle.setText("回复"+authorid+"的好友请求:");
        } else if (type.equals(Constant.EDTDIALOG_TYPE_UPDATE_NAME)) {
            txtTitle.setText("修改备注为：");
            btnSure.setText("确定");
            btnCancel.setText("取消");
        }else if (type.equals(Constant.EDTDIALOG_TYPE_WANT_ADD_FRIEND)){
            txtTitle.setText("想要加我为好友，就说点什么吧！");
            btnSure.setText("加好友");
            btnCancel.setText("容我再考虑一下");
        }
    }
}
