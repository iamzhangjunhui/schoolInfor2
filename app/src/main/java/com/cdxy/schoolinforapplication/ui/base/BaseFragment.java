package com.cdxy.schoolinforapplication.ui.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    private Toast toast;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    public abstract void init();

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
