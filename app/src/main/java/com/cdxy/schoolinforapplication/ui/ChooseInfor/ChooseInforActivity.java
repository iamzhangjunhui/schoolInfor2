package com.cdxy.schoolinforapplication.ui.ChooseInfor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.adapter.Text1Adapter;
import com.cdxy.schoolinforapplication.adapter.Text1ListAdapter;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.ui.widget.ScrollListView;
import com.cdxy.schoolinforapplication.util.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//系、班级、民族的选择
public class ChooseInforActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.scroll_list_infor)
    ScrollListView scrollListInfor;
    private String selectDepartment;
    private Text1Adapter arrayAdapter;
    private Text1ListAdapter listAdapterClass;
    private List<String> listClass;
    private String flagSelectInforFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_infor);
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
        init();
    }

    @Override
    public void init() {
        Intent intent=getIntent();
        flagSelectInforFrom=intent.getStringExtra("flagSelectInforFrom");
        selectDepartment=intent.getStringExtra("department");
        //选择系
        if (flagSelectInforFrom.equals(Constant.FLAG_REQURST_FROM_DEPARTMENT)) {
            txtTitle.setText("选择系");
            arrayAdapter = new Text1Adapter(ChooseInforActivity.this, getResources().getStringArray(R.array.department));
            scrollListInfor.setAdapter(arrayAdapter);
            scrollListInfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String department = arrayAdapter.getItem(i).toString();
                    if (!TextUtils.isEmpty(department)) {
                        Intent intent = new Intent();
                        intent.putExtra("department", department);
                        setResult(Constant.RESULT_CODE_CHOOSEDEPARTMENT, intent);
                        finish();
                    }
                }
            });
            //选择班级
        }else if (flagSelectInforFrom.equals(Constant.FLAG_REQURST_FROM_CLASS)){
            txtTitle.setText("选择班级");
            listClass=new ArrayList<>();
            listAdapterClass=new Text1ListAdapter(ChooseInforActivity.this,listClass);
            scrollListInfor.setAdapter(listAdapterClass);
            switch (selectDepartment){
                case "计算机系":
                    if (listClass!=null){
                        listClass.clear();
                    }
                    String[] clazz=getResources().getStringArray(R.array.计算机系);
                    for (int i=0;i<clazz.length;i++){
                        listClass.add(clazz[i]);
                    }
                    listAdapterClass.notifyDataSetChanged();
                    break;
                case "经管系":
                    if (listClass!=null){
                        listClass.clear();
                    }
                    clazz=getResources().getStringArray(R.array.经管系);
                    for (int i=0;i<clazz.length;i++){
                        listClass.add(clazz[i]);
                    }
                    listAdapterClass.notifyDataSetChanged();
                    break;
                case "云计算系":
                    if (listClass!=null){
                        listClass.clear();
                    }
                    clazz=getResources().getStringArray(R.array.云计算系);
                    for (int i=0;i<clazz.length;i++){
                        listClass.add(clazz[i]);
                    }
                    listAdapterClass.notifyDataSetChanged();
                    break;
            }
            scrollListInfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String clazz = listAdapterClass.getItem(i).toString();
                    if (!TextUtils.isEmpty(clazz)) {
                        Intent intent = new Intent();
                        intent.putExtra("clazz", clazz);
                        setResult(Constant.RESULT_CODE_CHOOSECLASS, intent);
                        finish();
                    }
                }
            });
            //选择民族
        }else {
            txtTitle.setText("选择民族");
            arrayAdapter=new Text1Adapter(ChooseInforActivity.this,getResources().getStringArray(R.array.nation));
            scrollListInfor.setAdapter(arrayAdapter);
            scrollListInfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String nation = arrayAdapter.getItem(i).toString();
                    if (!TextUtils.isEmpty(nation)) {
                        Intent intent = new Intent();
                        intent.putExtra("nation", nation);
                        setResult(Constant.RESULT_CODE_CHOOSENATION, intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
               ScreenManager.getScreenManager().popActivty(this);
        }
    }
}
