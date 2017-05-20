package com.cdxy.schoolinforapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.util.GetUserInfor;
import com.cdxy.schoolinforapplication.util.SharedPreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInformationActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_realname)
    TextView txtRealname;
    @BindView(R.id.txt_department)
    TextView txtDepartment;
    @BindView(R.id.txt_class)
    TextView txtClass;
    @BindView(R.id.txt_student_id)
    TextView txtStudentId;
    @BindView(R.id.txt_sex)
    TextView txtSex;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;
    @BindView(R.id.txt_nation)
    TextView txtNation;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.txt_hobby)
    TextView txtHobby;
    @BindView(R.id.activity_my_information)
    LinearLayout activityMyInformation;
    @BindView(R.id.ly_department)
    LinearLayout lyDepartment;
    @BindView(R.id.ly_class)
    LinearLayout lyClass;
    @BindView(R.id.ly_student_id)
    LinearLayout lyStudentId;
    private UserInforEntity userInfor;
    private String userId;
    private static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ScreenManager.getScreenManager().pushActivity(this);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void init() {
        txtTitle.setText("我的个人信息");
        txtRight.setVisibility(View.VISIBLE);
        userId = SharedPreferenceManager.instance(MyInformationActivity.this).getUserInfor().getUserid();
        id = userId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetUserInfor.getMyInfor(MyInformationActivity.this, userId);
            }
        }).start();
        userInfor = SharedPreferenceManager.instance(MyInformationActivity.this).getUserInfor();
        if (userInfor != null) {
            setData(userInfor);
            String i=userInfor.getShenfen();
            if (userInfor.getShenfen().equals("teacher")) {
                lyDepartment.setVisibility(View.GONE);
                lyClass.setVisibility(View.GONE);
                lyStudentId.setVisibility(View.GONE);
            }
        }
    }

    public static String getUserid() {
        return id;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                ScreenManager.getScreenManager().popActivty(this);
                break;
            case R.id.txt_right:
                Intent intent = new Intent(MyInformationActivity.this, ModifyMyInforActivity.class);
                intent.putExtra("userInfor", userInfor);
                startActivityForResult(intent, 0);
                break;
        }
    }

    private void setData(UserInforEntity userInfor) {
        if (!TextUtils.isEmpty(userInfor.getUserid())) {
            String nickName = userInfor.getNicheng();
            if (!nickName.equals("null")) {
                txtNickname.setText(nickName);
            }
            String name = userInfor.getXingming();
            if (!name.equals("null")) {
                txtRealname.setText(name);
            }
            String department = userInfor.getXibie();
            if (!department.equals("null")) {
                txtDepartment.setText(department);
            }
            String clazz = userInfor.getBanji();
            if (!clazz.equals("null")) {
                txtClass.setText(clazz);
            }
            String studentid = userInfor.getXuehao();
            if (!studentid.equals("null")) {
                txtStudentId.setText(studentid);
            }
            String sex = userInfor.getXingbie();
            if (!sex.equals("null")) {
                txtSex.setText(sex);
            }
            String birthday = userInfor.getShengri();
            if (!birthday.equals("null")) {
                txtBirthday.setText(birthday);
            }
            String nation = userInfor.getMinzu();
            if (!nation.equals("null")) {
                txtNation.setText(nation);
            }
            String address = userInfor.getJia();
            if (!address.equals("null")) {
                txtAddress.setText(address);
            }
            String hobby = userInfor.getXingqu();
            if (!hobby.equals("null")) {
                txtHobby.setText(hobby);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GetUserInfor.getMyInfor(MyInformationActivity.this, userId);
                }
            }).start();
            String resultJson = data.getStringExtra("userInforJsonString");
            UserInforEntity userInforEntity = SchoolInforManager.gson.fromJson(resultJson, UserInforEntity.class);
            setData(userInforEntity);
        }
    }
}
