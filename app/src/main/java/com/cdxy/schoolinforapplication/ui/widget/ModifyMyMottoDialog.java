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

import com.cdxy.schoolinforapplication.R;
import com.openim.android.dexposed.callbacks.XCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/3/23.
 */

public class ModifyMyMottoDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.edt_modify_my_motto)
    EditText edtModifyMyMotto;
    @BindView(R.id.btn_save_modify_motto)
    Button btnSaveModifyMotto;
    @BindView(R.id.btn_cancel_my_modify_motto)
    Button btnCancelMyModifyMotto;
    public  String newMotto;
    private ModifyMottoDialogListener modifyMottoDialogListener;
    private Activity activity;

    public ModifyMyMottoDialog(@NonNull Context context, @StyleRes int themeResId, ModifyMottoDialogListener modifyMottoDialogListener,Activity activity) {
        super(context, themeResId);
        this.modifyMottoDialogListener = modifyMottoDialogListener;
        this.activity=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_modify_my_motto);
        ButterKnife.bind(this);
       WindowManager.LayoutParams   params= getWindow().getAttributes();
        params.width=activity.getWindowManager().getDefaultDisplay().getWidth()-40;
        getWindow().setAttributes(params);
        btnCancelMyModifyMotto.setOnClickListener(this);
        btnSaveModifyMotto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        newMotto = edtModifyMyMotto.getText().toString();
        modifyMottoDialogListener.onClick(view);
    }

    public interface ModifyMottoDialogListener {
        void onClick(View view);
    }
}
