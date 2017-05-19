package com.cdxy.schoolinforapplication.ui.Message;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.ui.base.BaseFragment;
import com.cdxy.schoolinforapplication.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.img_icon1)
    ImageView imgIcon1;
    @BindView(R.id.layout_important_message)
    LinearLayout layoutImportantMessage;
    @BindView(R.id.img_icon2)
    ImageView imgIcon2;
    @BindView(R.id.layout_not_important_message)
    LinearLayout layoutNotImportantMessage;
    @BindView(R.id.img_icon3)
    ImageView imgIcon3;
    @BindView(R.id.layout_my_message)
    LinearLayout layoutMyMessage;
//    @BindView(R.id.not_see_list)
//    Button button;
    private String identity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addListener();
    }

    @Override
    public void init() {
//      identity= SharedPreferenceManager.instance(getContext()).getUserInfor().getShenfen();
        String identity="老师";
        if (identity.equals("老师")){
            layoutMyMessage.setVisibility(View.VISIBLE);
        }
    }
    private  void addListener(){
        layoutImportantMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MessageListActivity.class);
                intent.putExtra("message_type", Constant.IMPORTANT_MESSAGE);
                startActivity(intent);
            }
        });
        layoutNotImportantMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MessageListActivity.class);
                intent.putExtra("message_type", Constant.NOT_IMPORTANT_MESSAGE);
                startActivity(intent);
            }
        });
        layoutMyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MessageListActivity.class);
                intent.putExtra("message_type", Constant.MY_SEND_MESSAGE);
                startActivity(intent);
            }
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getContext(), SeeMessageStudentsActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
