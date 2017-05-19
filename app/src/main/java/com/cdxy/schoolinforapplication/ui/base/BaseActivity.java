package com.cdxy.schoolinforapplication.ui.base;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
   public abstract void  init();
    public void toast(String s) {
        if (toast == null) {
            toast = Toast.makeText(SchoolInforManager.getContext(), s, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public void toastLongShow(String s) {
        if (toast == null) {
            toast = Toast.makeText(SchoolInforManager.getContext(), s, Toast.LENGTH_LONG);
        }
        toast.show();
    }

}
