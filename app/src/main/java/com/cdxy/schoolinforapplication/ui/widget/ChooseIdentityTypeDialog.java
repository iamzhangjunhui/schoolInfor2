package com.cdxy.schoolinforapplication.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.cdxy.schoolinforapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/3/23.
 */

public class ChooseIdentityTypeDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.btn_teacher)
    Button btnTeacher;
    @BindView(R.id.btn_student)
    Button btnStudent;
    private ChooseIdentityTypeDialogListener chooseIdentityTypeDialogListener;
    private Activity activity;

    public ChooseIdentityTypeDialog(@NonNull Context context, @StyleRes int themeResId, ChooseIdentityTypeDialogListener chooseIdentityTypeDialogListener, Activity activity) {
        super(context, themeResId);
        this.activity = activity;
        this.chooseIdentityTypeDialogListener = chooseIdentityTypeDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_hint_choose_identity_type);
        ButterKnife.bind(this);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = activity.getWindowManager().getDefaultDisplay().getWidth() - 40;
        params.height = 300;
        getWindow().setAttributes(params);
        btnStudent.setOnClickListener(this);
        btnTeacher.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        chooseIdentityTypeDialogListener.onClick(view);
    }

    public  interface ChooseIdentityTypeDialogListener {
        public void onClick(View view);
    }
}
