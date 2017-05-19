package com.cdxy.schoolinforapplication.ui.load;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContactService;
import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.LoginReturnEntity;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.model.topic.TopicEntity;
import com.cdxy.schoolinforapplication.ui.MainActivity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.ui.widget.ChooseIdentityTypeDialog;
import com.cdxy.schoolinforapplication.util.GetUserInfor;
import com.cdxy.schoolinforapplication.util.HttpUtil;
import com.cdxy.schoolinforapplication.util.SharedPreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

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
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edt_login_name)
    EditText edtLoginName;
    @BindView(R.id.edt_login_password)
    EditText edtLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    public static YWIMKit ywimKit;
    public static IYWContactService iywContactService;
    @BindView(R.id.progress)
    ProgressBar progress;
    private String loginName;
    private ChooseIdentityTypeDialog chooseIdentityTypeDialog;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
        //如果注册的时候返回说账号已经注册过了，返回登录界面是显示登录名
        loginName = getIntent().getStringExtra("mRegisterName");
        edtLoginName.setText(loginName);
        //如果本地有登录信息就实现自动登录
        autoLogin();
        gson = new Gson();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                chooseIdentityTypeDialog = new ChooseIdentityTypeDialog(LoginActivity.this, R.style.MyDialog, new ChooseIdentityTypeDialog.ChooseIdentityTypeDialogListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.btn_teacher:
                                chooseIdentityTypeDialog.dismiss();
                                break;
                            case R.id.btn_student:
                                Intent intent = new Intent(LoginActivity.this, RegisterCodeActivity.class);
                                startActivity(intent);
                                chooseIdentityTypeDialog.dismiss();
                                break;
                        }
                    }
                }, LoginActivity.this);
                chooseIdentityTypeDialog.show();
                break;
            case R.id.btn_login:
                loginName = edtLoginName.getText().toString();
                String loginPassword = edtLoginPassword.getText().toString();
                if (TextUtils.isEmpty(loginName)) {
                    toast("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(loginPassword)) {
                    toast("请输入登录密码");
                    return;
                }
                login(loginName, loginPassword);
            default:
                break;
        }
    }

    //苏杭    登陆接口
    public void login(final String userid, final String password) {
        progress.setVisibility(View.VISIBLE);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = HttpUtil.getClient();
                Request request = new Request.Builder().url(HttpUrl.LOGIN + "?userid=" + userid + "&&password=" + password).get().build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ReturnEntity<LoginReturnEntity> returnEntity = gson.fromJson(s, ReturnEntity.class);
                if (returnEntity != null) {
                    returnEntity = gson.fromJson(s, new TypeToken<ReturnEntity<LoginReturnEntity>>() {
                    }.getType());
                    //如果登陆成功
                    if (returnEntity.getCode() == 1) {
                        SharedPreferenceManager.instance(LoginActivity.this).setMyPassword(password);
                        LoginReturnEntity loginReturnEntity = returnEntity.getData();
                        aliLogin(loginReturnEntity.getUserid(), loginReturnEntity.getPassword());
                        GetUserInfor.getMyInfor(LoginActivity.this, loginReturnEntity.getUserid());
                    } else {
                        toast("登录出现异常");
                    }

                }
            }
        });
    }

    private void aliLogin(final String userid, final String password) {
        ywimKit = YWAPI.getIMKitInstance(userid, SchoolInforManager.appKay);
        iywContactService = ywimKit.getContactService();
        //开始登录(测试使用，到时正式使用的时候需要放在登录我们的服务器成功之后)
        IYWLoginService loginService = ywimKit.getLoginService();
        YWLoginParam param = new YWLoginParam(userid, password, SchoolInforManager.appKay);
        loginService.login(param, new IWxCallback() {
            @Override
            public void onSuccess(Object... objects) {
                Observable.just("").observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        progress.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                Observable.just(s).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        progress.setVisibility(View.GONE);
                        toast(s);
                    }
                });
            }

            @Override
            public void onProgress(int i) {

            }
        });
    }


    private void autoLogin() {
        UserInforEntity userInforEntity = SharedPreferenceManager.instance(LoginActivity.this).getUserInfor();
        if (userInforEntity != null) {
            String userid = userInforEntity.getUserid();
            String password = SharedPreferenceManager.instance(LoginActivity.this).getMyPassword();
            if (!TextUtils.isEmpty(userid) && (!TextUtils.isEmpty(password))) {
                ywimKit = YWAPI.getIMKitInstance(userid, SchoolInforManager.appKay);
                iywContactService = ywimKit.getContactService();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}