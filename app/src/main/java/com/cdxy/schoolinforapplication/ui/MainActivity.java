package com.cdxy.schoolinforapplication.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.HttpUrl;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.SchoolInforManager;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.model.ReturnEntity;
import com.cdxy.schoolinforapplication.model.UserInfor.UserInforEntity;
import com.cdxy.schoolinforapplication.ui.Message.SendMessageActivity;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.ui.chat.MyFriendActivity;
import com.cdxy.schoolinforapplication.ui.load.LoginActivity;
import com.cdxy.schoolinforapplication.ui.my.ModifyMyPswActivity;
import com.cdxy.schoolinforapplication.ui.my.MyInformationActivity;
import com.cdxy.schoolinforapplication.ui.topic.AddNewTopicActivity;
import com.cdxy.schoolinforapplication.ui.widget.ChooseWayDialog;
import com.cdxy.schoolinforapplication.ui.widget.DragLayout;
import com.cdxy.schoolinforapplication.ui.widget.ModifyMyMottoDialog;
import com.cdxy.schoolinforapplication.util.Constant;
import com.cdxy.schoolinforapplication.util.GetUserInfor;
import com.cdxy.schoolinforapplication.util.HttpUtil;
import com.cdxy.schoolinforapplication.util.SharedPreferenceManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_my_icon)
    ImageView imgMyIcon;
    @BindView(R.id.txt_my_name)
    TextView txtMyName;
    @BindView(R.id.txt_my_department)
    TextView txtMyDepartment;
    @BindView(R.id.txt_my_clazz)
    TextView txtMyClazz;
    @BindView(R.id.txt_my_motto)
    TextView txtMyMotto;
    @BindView(R.id.txt_my_information)
    TextView txtMyInformation;
    @BindView(R.id.txt_my_modify_psw)
    TextView txtMyModifyPsw;
    @BindView(R.id.txt_exit)
    TextView txtExit;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.img_bottom_chat)
    ImageView imgBottomChat;
    @BindView(R.id.txt_bottom_chat)
    TextView txtBottomChat;
    @BindView(R.id.layout_bottom_chat)
    LinearLayout layoutBottomChat;
    @BindView(R.id.img_bottom_topic)
    ImageView imgBottomTopic;
    @BindView(R.id.txt_bottom_topic)
    TextView txtBottomTopic;
    @BindView(R.id.layout_bottom_topic)
    LinearLayout layoutBottomTopic;
    @BindView(R.id.img_bottom_message)
    ImageView imgBottomMessage;
    @BindView(R.id.txt_bottom_message)
    TextView txtBottomMessage;
    @BindView(R.id.layout_bottom_message)
    LinearLayout layoutBottomMessage;
    @BindView(R.id.draglayout)
    DragLayout draglayout;
    private long exitTime = 0;
    private boolean isOpon;//侧滑栏是否打开
    private FragmentManager fragmentManager;
    private TextView[] textViews;
    private ImageView[] imageViews;
    private Fragment[] fragments;
    private int oldPos = 1;
    private ModifyMyMottoDialog modifyMyMottoDialog;
    public static String TAG = "SchoolInforManager";
    private static final int MSG_SET_TAGS = 1001;
    private Handler mHandler;
    private Set<String> tags = new HashSet<>();
    private TagAliasCallback mAliasCallback;
    private ChooseWayDialog chooseWayDialog;
    private File file;
    private UserInforEntity userInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
        txtTitle.setText("话题");
        btnRight.setText("创建话题");
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewTopicActivity.class);
                startActivity(intent);
            }
        });
        setFragments(1);
    }

    @Override
    public void init() {
        //获取是否是从添加话题处返回
        if (fragmentManager == null) {

            fragmentManager = getSupportFragmentManager();
        }
        fragments = new Fragment[]{fragmentManager.findFragmentById(R.id.chat_fragment),
                fragmentManager.findFragmentById(R.id.topic_fragment),
                fragmentManager.findFragmentById(R.id.message_fragment)};
        textViews = new TextView[]{txtBottomChat, txtBottomTopic, txtBottomMessage};
        imageViews = new ImageView[]{imgBottomChat, imgBottomTopic, imgBottomMessage};
        userInfor = SharedPreferenceManager.instance(MainActivity.this).getUserInfor();
        if (userInfor != null) {
            String icon = userInfor.getTouxiang();
            if (!TextUtils.isEmpty(icon)) {
                Glide.with(MainActivity.this).load(icon).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgMyIcon);
                Glide.with(MainActivity.this).load(icon).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgIcon);
            } else {
                Glide.with(MainActivity.this).load(R.drawable.icon).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgMyIcon);
                Glide.with(MainActivity.this).load(R.drawable.icon).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgIcon);
            }
            String name = userInfor.getXingming();
            if (!TextUtils.equals(name, "null")) {
                txtMyName.setText(name);
            }
            String clazz = userInfor.getBanji();
            if (!TextUtils.equals(clazz, "null")) {
                txtMyClazz.setText(clazz);
            }
            String motto = userInfor.getZuoyouming();
            if (TextUtils.equals(motto, "null") || TextUtils.isEmpty(motto)) {
                txtMyMotto.setText("我的座右铭");
            } else {
                txtMyMotto.setText(motto);
            }
            String department = userInfor.getXibie();
            if (!TextUtils.equals(department, "null")) {
                txtMyDepartment.setText(department);
            }
        }

    }

    //判断双击时间间隔是否小于2秒
    public void exitAppBy2Click() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            exitTime = System.currentTimeMillis();
        } else {
            ScreenManager.getScreenManager().appExit(MainActivity.this);
        }
    }

    private void setFragments(int selectPos) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (selectPos < oldPos) {
            //该方法实现的是fragment跳转时的动画效果
            transaction.setCustomAnimations(R.anim.fragment_in_1, R.anim.fragment_out_1);
        } else if (selectPos > oldPos) {
            transaction.setCustomAnimations(R.anim.fragment_in_2, R.anim.fragment_out_2);
        }
        transaction.commit();
        oldPos = selectPos;
        for (int i = 0; i < textViews.length; i++) {
            if (i == selectPos) {
                transaction.show(fragments[i]);
                textViews[i].setTextColor(getResources().getColor(R.color.text_bottom_tap_color));
                setImage(i, true);
            } else {
                transaction.hide(fragments[i]);
                textViews[i].setTextColor(getResources().getColor(R.color.white));
                setImage(i, false);
            }
        }
        JPushAddTags();
    }

    //极光推送添加Tags
    private void JPushAddTags() {
        //极光推送设置Tag的回调
        mAliasCallback = new TagAliasCallback() {
            @Override
            public void gotResult(int code, String alias, Set<String> tags) {
                String logs;
                switch (code) {
                    case 0:
                        logs = "Set tag success";
                        SharedPreferenceManager.instance(MainActivity.this).setIsAddtag(true);
                        Log.i(TAG, logs);
                        // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                        break;
                    case 6002:
                        logs = "Failed to tags due to timeout. Try again after 60s.";
                        Log.i(TAG, logs);
                        // 延迟 60 秒来调用 Handler 设置别名
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, alias), 1000 * 60);
                        break;
                    default:
                        logs = "Failed with errorCode = " + code;
                        Log.e(TAG, logs);
                }
                Toast.makeText(MainActivity.this, logs, Toast.LENGTH_SHORT).show();
            }
        };
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_SET_TAGS:
                        Log.d(TAG, "Set alias in handler.");
                        // 调用 JPush 接口来设置标签。
                        JPushInterface.setTags(MainActivity.this, tags, mAliasCallback);
                        break;
                    default:
                        Log.i(TAG, "Unhandled msg - " + msg.what);
                }
            }
        };
        UserInforEntity userInforEntity = SharedPreferenceManager.instance(MainActivity.this).getUserInfor();
        if (userInforEntity != null) {
            String clazz = userInforEntity.getBanji();
            String xibie = userInforEntity.getXibie();
            if (!TextUtils.isEmpty(clazz))
                tags.add(clazz);
            if (!TextUtils.isEmpty(xibie))
                tags.add(xibie);
        }
        boolean isAddTags = SharedPreferenceManager.instance(MainActivity.this).getIsAddtag();
        if (!isAddTags) {
            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tags));
        }
    }

    private void setImage(int selectPos, boolean siselect) {
        switch (selectPos) {
            case 0:
                imageViews[0].setImageDrawable(
                        siselect ? getResources().getDrawable(R.drawable.bottom_tap_chat_true) :
                                getResources().getDrawable(R.drawable.bottom_tap_chat_false));
                break;
            case 1:
                imageViews[1].setImageDrawable(
                        siselect ? getResources().getDrawable(R.drawable.bottom_tap_topic_true) :
                                getResources().getDrawable(R.drawable.bottom_tap_topic_false));
                break;
            case 2:
                imageViews[2].setImageDrawable(
                        siselect ? getResources().getDrawable(R.drawable.bottom_tap_message_true) :
                                getResources().getDrawable(R.drawable.bottom_tap_message_false));
                break;
        }
    }

    //双击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitAppBy2Click();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_icon:
                //点击顶部导航栏的头像后，判断侧滑栏是否打开，再执行打开或关闭操作
                if (isOpon) {
                    draglayout.close();
                    isOpon = false;

                } else {
                    draglayout.open();
                    isOpon = true;
                }
                break;
            case R.id.img_my_icon:
                chooseWayDialog = new ChooseWayDialog(MainActivity.this, R.style.MyDialog, new ChooseWayDialog.ChooseWayDialogListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.txt_way1:
                                //打开系统拍照程序，选择拍照图片
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                                if (!file.exists()) {
                                    try {
                                        file.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                // 下面这句指定调用相机拍照后的照片存储的路径
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    Toast.makeText(MainActivity.this, "拍照的权限申请失败", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                MainActivity.this.startActivityForResult(intent, Constant.REQUEST_CODE_CAMERA);
                                chooseWayDialog.dismiss();
                                break;
                            case R.id.txt_way2:
                                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                MainActivity.this.startActivityForResult(intent, Constant.REQUEST_CODE_PICTURE);
                                chooseWayDialog.dismiss();
                                break;
                        }
                    }
                }, MainActivity.this, Constant.CHOOSE_WAY_DIALOG_TYPE_GET_PHOTO);
                chooseWayDialog.show();
                break;
            case R.id.txt_my_motto:
                modifyMyMottoDialog = new ModifyMyMottoDialog(MainActivity.this, R.style.MyDialog, new ModifyMyMottoDialog.ModifyMottoDialogListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.btn_save_modify_motto:
                                //此处将修改后的motto上传至服务器。
                                String userid = SharedPreferenceManager.instance(MainActivity.this).getUserInfor().getUserid();
                                if (TextUtils.isEmpty(userid)) {
                                    toast("登录失效");
                                } else {
                                    updateMyMotto(userid, modifyMyMottoDialog.newMotto);
                                }
                                modifyMyMottoDialog.dismiss();
                                break;
                            case R.id.btn_cancel_my_modify_motto:
                                modifyMyMottoDialog.dismiss();
                                break;

                        }
                    }
                }, MainActivity.this);
                modifyMyMottoDialog.show();
                break;
            case R.id.txt_my_information:
                //进入我的信息详情界面
                Intent intent = new Intent(MainActivity.this, MyInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_my_modify_psw:
                //进入修改密码界面
                intent = new Intent(MainActivity.this, ModifyMyPswActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_exit:
                SharedPreferenceManager.instance(MainActivity.this).setUserInfor("");
                SharedPreferenceManager.instance(MainActivity.this).setMyPassword("");
                ScreenManager.getScreenManager().appExit(this);
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_bottom_chat:
                setFragments(0);
                txtTitle.setText("会话中心");
                btnRight.setText("我的好友");
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, MyFriendActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.layout_bottom_topic:
                txtTitle.setText("话题");
                btnRight.setText("创建话题");
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, AddNewTopicActivity.class);
                        startActivityForResult(intent, 1);
                    }
                });
                setFragments(1);
                break;
            case R.id.layout_bottom_message:
                txtTitle.setText("消息中心");
                //                String identity=SharedPreferenceManager.instance(MainActivity.this).getSharedPreferences().getString(SharedPreferenceManager.IDENTITY,null);
                String identity = "老师";
                if (identity.equals("老师")) {
                    btnRight.setText("发送消息");
                    btnRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, SendMessageActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    btnRight.setText("");
                }
                setFragments(2);
                break;

        }
    }

    private void updateMyMotto(final String userid, final String zuoyouming) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = HttpUtil.getClient();
                Request request = new Request.Builder().url(HttpUrl.UPDATE_MY_MOTTO + "?userid=" + userid + "&&zuoyouming=" + zuoyouming).get().build();
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
                ReturnEntity returnEntity = SchoolInforManager.gson.fromJson(s, ReturnEntity.class);
                if (returnEntity != null) {
                    if (returnEntity.getCode() == 1) {
                        GetUserInfor.getMyInfor(MainActivity.this, userid);
                        txtMyMotto.setText(zuoyouming);
                    } else {
                        toast(returnEntity.getMsg() + "");
                    }
                } else {
                    toast("修改座右铭出错");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
            } else {
                //获取拍照后返回的图片
                if (requestCode == Constant.REQUEST_CODE_CAMERA) {
                    //调用系统裁剪工具对图片进行裁剪
                    startPhotoZoom(Uri.fromFile(file));
                }
                //从相册获取图片
                if (requestCode == Constant.REQUEST_CODE_PICTURE) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String picturePath = c.getString(columnIndex);
                    File file1 = new File(picturePath);
                    if (file1 != null) {
                        //调用系统裁剪工具对图片进行裁剪
                        startPhotoZoom(Uri.fromFile(file1));
                    }

                }
                if (requestCode == 3) {
                    //获取裁剪后的图片
                    if (data != null) {
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "myImage";
                        //创建文件夹
                        File folder = new File(path);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        //创建文件，该文件是以当前时间的时间戳来命名的，所以不可能已存在
                        String imagePath = path + File.separator + System.currentTimeMillis() + ".jpg";
                        File imageFile = new File(imagePath);
                        try {
                            imageFile.createNewFile();
                            saveMyBitmap(imagePath, bitmap);
                            updateMyIcon(imagePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪后的图片
     *
     * @param path
     * @param mBitmap
     * @return
     */
    public boolean saveMyBitmap(String path, Bitmap mBitmap) {
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(new File(path));
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateMyIcon(final String path) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = HttpUtil.getClient();
                MediaType mediaType = MediaType.parse("image/png");
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                final File file = new File(path);
                if (file != null) {
                    builder.addFormDataPart("file", file.getName(), MultipartBody.create(mediaType, file));
                }
                builder.addFormDataPart("userid", userInfor.getUserid());
                MultipartBody multipartBody = builder.build();
                Request request = new Request.Builder().url(HttpUrl.UPDATE_MY_HEAD_PORTRAIT).post(multipartBody).build();
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
                ReturnEntity returnEntity = SchoolInforManager.gson.fromJson(s, ReturnEntity.class);
                if (returnEntity != null) {
                    if (returnEntity.getCode() == 1) {
                        if (file != null) {
                            GetUserInfor.getMyInfor(MainActivity.this, userInfor.getUserid());
                            Glide.with(MainActivity.this).load(file).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgMyIcon);
                            Glide.with(MainActivity.this).load(file).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgIcon);
                        } else {
                            Glide.with(MainActivity.this).load(R.drawable.students).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgMyIcon);
                            Glide.with(MainActivity.this).load(R.drawable.students).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(imgIcon);
                        }
                    } else {
                        toast(returnEntity.getMsg() + "");
                    }
                } else {
                    toast("上传头像出错");
                }

            }
        });
    }
}
