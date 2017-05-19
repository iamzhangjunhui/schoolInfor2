package com.cdxy.schoolinforapplication.ui.load;

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
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.ui.ChooseInfor.ChooseInforActivity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.util.Constant;
import com.cdxy.schoolinforapplication.util.HttpUtil;
import com.google.gson.Gson;

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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
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
    @BindView(R.id.edt_nickname)
    EditText edtNickname;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    @BindView(R.id.progress)
    ProgressBar progress;
    private String mRegisterName;
    private String mDepartment;
    private String mClazz;
    private String mNickName;
    private String mName;
    private String mStudentId;
    private String mSex;
    private String mBirthday;
    private String mNation;
    private String mAddress;
    private String mHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
        init();
        Intent intent = getIntent();
        mRegisterName = intent.getStringExtra("registerName");
    }

    @Override
    public void init() {
        txtTitle.setText("信息填写");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                ScreenManager.getScreenManager().popActivty(this);
                break;
            case R.id.txt_department:
                Intent intent = new Intent(RegisterActivity.this, ChooseInforActivity.class);
                intent.putExtra("flagSelectInforFrom", Constant.FLAG_REQURST_FROM_DEPARTMENT);
                startActivityForResult(intent, Constant.REQUEST_CODE_CHOOSEDEPARTMENT);
                break;
            case R.id.txt_class:
                intent = new Intent(RegisterActivity.this, ChooseInforActivity.class);
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
                intent = new Intent(RegisterActivity.this, ChooseInforActivity.class);
                intent.putExtra("flagSelectInforFrom", Constant.FLAG_REQURST_FROM_NATION);
                startActivityForResult(intent, Constant.REQUEST_CODE_CHOOSENATION);
                break;
            case R.id.txt_birthday:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtBirthday.setText(i + "年" + (i1 + 1) + "月" + i2 + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.submit_register:
                mName = edtRealname.getText().toString();
                mNickName = edtNickname.getText().toString();
                mStudentId = edtStudentId.getText().toString();
                int checkSexId = rgSex.getCheckedRadioButtonId();
                if (checkSexId == R.id.girl) {
                    mSex = "女";
                } else {
                    mSex = "男";
                }
                mBirthday = txtBirthday.getText().toString();
                mAddress = edtAddress.getText().toString();
                mHobby = edtHobby.getText().toString();
                if (TextUtils.isEmpty(mName)) {
                    toast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(mNickName)) {
                    toast("请输入昵称");
                    return;
                }
                if (TextUtils.isEmpty(mDepartment)) {
                    toast("请选择所在系");
                    return;
                }
                if (TextUtils.isEmpty(mClazz)) {
                    toast("请选择所在班级");
                    return;
                }
                if (TextUtils.isEmpty(mStudentId)) {
                    toast("请输入学号");
                    return;
                }
                UserInforEntity userInforEntity = new UserInforEntity(mRegisterName, mNickName, mName, mDepartment, mClazz,
                        mStudentId, mSex, mBirthday, mNation, mAddress, mHobby);
                Gson gson = new Gson();
                String json = gson.toJson(userInforEntity);
                updateUserInfor(json);
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
                mDepartment = department;
            }
        }
        if (requestCode == Constant.REQUEST_CODE_CHOOSECLASS && resultCode == Constant.RESULT_CODE_CHOOSECLASS) {
            String clazz = data.getStringExtra("clazz");
            if (!TextUtils.isEmpty(clazz)) {
                txtClass.setText(clazz);
                mClazz = clazz;
            }
        }
        if (requestCode == Constant.REQUEST_CODE_CHOOSENATION && resultCode == Constant.RESULT_CODE_CHOOSENATION) {
            String nation = data.getStringExtra("nation");
            if (!TextUtils.isEmpty(nation)) {
                txtNation.setText(nation);
                mNation = nation;
            }
        }
    }

    public void updateUserInfor(String userInforJsonString) {
        progress.setVisibility(View.VISIBLE);
        OkHttpClient okHttpClient = HttpUtil.getClient();
        final Request request = new Request.Builder().url(HttpUrl.UPDATE_MY_INFOR + "?userInfor=" + userInforJsonString).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Observable.just("注册失败，请检查一下网络是否连接").observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        progress.setVisibility(View.GONE);
                        toast(s);
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Observable.just(response.body().string()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        progress.setVisibility(View.GONE);
                        ScreenManager.getScreenManager().appExit(RegisterActivity.this);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("mRegisterName", mRegisterName);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
