package com.cdxy.schoolinforapplication.ui.my;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.ui.ChooseInfor.ChooseInforActivity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.util.Constant;
import com.cdxy.schoolinforapplication.util.GetUserInfor;
import com.cdxy.schoolinforapplication.util.HttpUtil;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ModifyMyInforActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edt_nickname)
    EditText edtNickname;
    @BindView(R.id.edt_realname)
    EditText edtRealname;
    @BindView(R.id.txt_department)
    TextView txtDepartment;
    @BindView(R.id.txt_class)
    TextView txtClass;
    @BindView(R.id.edt_student_id)
    EditText edtStudentId;
    @BindView(R.id.girl)
    RadioButton girl;
    @BindView(R.id.boy)
    RadioButton boy;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;
    @BindView(R.id.txt_nation)
    TextView txtNation;
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.edt_hobby)
    EditText edtHobby;
    @BindView(R.id.submit_register)
    Button submitRegister;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    @BindView(R.id.progress)
    ProgressBar progress;
    private UserInforEntity userInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_infor);
        ScreenManager.getScreenManager().pushActivity(this);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void init() {
        txtTitle.setText("修改我的个人信息");
        userInfor = (UserInforEntity) getIntent().getSerializableExtra("userInfor");
        setData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                ScreenManager.getScreenManager().popActivty(this);
                break;
            case R.id.txt_department:
                Intent intent = new Intent(ModifyMyInforActivity.this, ChooseInforActivity.class);
                intent.putExtra("flagSelectInforFrom", Constant.FLAG_REQURST_FROM_DEPARTMENT);
                startActivityForResult(intent, Constant.REQUEST_CODE_CHOOSEDEPARTMENT);
                break;
            case R.id.txt_class:
                intent = new Intent(ModifyMyInforActivity.this, ChooseInforActivity.class);
                intent.putExtra("flagSelectInforFrom", Constant.FLAG_REQURST_FROM_CLASS);
                String department = txtDepartment.getText().toString();
                if (TextUtils.isEmpty(department)) {
                    toast("请先选择你所在系");
                    return;
                } else {
                    intent.putExtra("department", department);
                    startActivityForResult(intent, Constant.REQUEST_CODE_CHOOSECLASS);
                }
                break;
            case R.id.txt_nation:
                intent = new Intent(ModifyMyInforActivity.this, ChooseInforActivity.class);
                intent.putExtra("flagSelectInforFrom", Constant.FLAG_REQURST_FROM_NATION);
                startActivityForResult(intent, Constant.REQUEST_CODE_CHOOSENATION);
                break;
            case R.id.txt_birthday:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(ModifyMyInforActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtBirthday.setText(i + "年" + (i1 + 1) + "月" + i2 + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.submit_register:
                String sex = null;
                if (girl.isChecked()) {
                    sex = "女";
                } else if (boy.isChecked()) {
                    sex = "男";
                }
                String oldDepartment = userInfor.getXibie();
                String nowDepartment = txtDepartment.getText().toString();
                String oldClazz = userInfor.getBanji();
                String nowClazz = txtClass.getText().toString();
                if ((!TextUtils.equals(oldDepartment, nowDepartment)) && TextUtils.equals(oldClazz, nowClazz)) {
                    toast("你修改了系，请将班级也修改一下");
                    return;
                }
                UserInforEntity userInforEntity = new UserInforEntity(userInfor.getUserid(), edtNickname.getText().toString(), edtRealname.getText().toString(),
                        txtDepartment.getText().toString(), txtClass.getText().toString(), edtStudentId.getText().toString(),
                        sex, txtBirthday.getText().toString(), txtNation.getText().toString(), edtAddress.getText().toString(),
                        edtHobby.getText().toString());
                String userInforJson = SchoolInforManager.gson.toJson(userInforEntity);
                updateUserInfor(userInforJson, userInfor.getUserid());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_CHOOSEDEPARTMENT && resultCode == Constant.RESULT_CODE_CHOOSEDEPARTMENT) {
            String department = data.getStringExtra("department");
            if (!TextUtils.isEmpty(department)) {
                txtDepartment.setText(department);
            }
        }
        if (requestCode == Constant.REQUEST_CODE_CHOOSECLASS && resultCode == Constant.RESULT_CODE_CHOOSECLASS) {
            String clazz = data.getStringExtra("clazz");
            if (!TextUtils.isEmpty(clazz)) {
                txtClass.setText(clazz);
            }
        }
        if (requestCode == Constant.REQUEST_CODE_CHOOSENATION && resultCode == Constant.RESULT_CODE_CHOOSENATION) {
            String nation = data.getStringExtra("nation");
            if (!TextUtils.isEmpty(nation)) {
                txtNation.setText(nation);
            }
        }
    }

    private void setData() {
        if (userInfor != null) {
            if (!TextUtils.isEmpty(userInfor.getUserid())) {
                String nickName = userInfor.getNicheng();
                if (!nickName.equals("null")) {
                    edtNickname.setText(nickName);
                }
                String name = userInfor.getXingming();
                if (!name.equals("null")) {
                    edtRealname.setText(name);
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
                    edtRealname.setText(studentid);
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
                    edtAddress.setText(address);
                }
                String hobby = userInfor.getXingqu();
                if (!hobby.equals("null")) {
                    edtHobby.setText(hobby);
                }
                String sex = userInfor.getXingbie();
                if (sex.equals("女")) {
                    rgSex.check(R.id.girl);
                } else if (sex.equals("男")) {
                    rgSex.check(R.id.boy);
                }
            }
        }
    }

    public void updateUserInfor(final String userInforJsonString, final String userid) {
        progress.setVisibility(View.VISIBLE);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = HttpUtil.getClient();
                final Request request = new Request.Builder().url(HttpUrl.UPDATE_MY_INFOR + "?userInfor=" + userInforJsonString).get().build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                progress.setVisibility(View.GONE);
                ReturnEntity returnEntity = SchoolInforManager.gson.fromJson(s, ReturnEntity.class);
                if (returnEntity != null) {
                    if (returnEntity.getCode() == 1) {
                        GetUserInfor.getMyInfor(ModifyMyInforActivity.this, userid);
                        Intent intent = new Intent();
                        intent.putExtra("userInforJsonString", userInforJsonString);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        toast(returnEntity.getMsg() + "");
                    }
                } else {
                    toast("修改个人信息出错");
                }

            }
        });
    }
}
