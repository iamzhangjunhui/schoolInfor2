package com.cdxy.schoolinforapplication.ui.topic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.ScreenManager;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;
import com.cdxy.schoolinforapplication.util.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowBigPhotosActivity extends BaseActivity  {

    @BindView(R.id.viewPager_big_photos)
    ViewPager viewPagerBigPhotos;
    @BindView(R.id.activity_new_big_photos)
    LinearLayout activityNewBigPhotos;
    private List<Object> photos;
    private int position;
    private PagerAdapter adapter;
    private List<View> views;
    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_photos);
        ButterKnife.bind(this);
        ScreenManager.getScreenManager().pushActivity(this);
        init();
        viewPagerBigPhotos.setAdapter(adapter);
        viewPagerBigPhotos.setCurrentItem(position);
    }

    @Override
    public void init() {
        Intent intent=getIntent();
        photos= (List<Object>) intent.getSerializableExtra("photos");
        position=intent.getIntExtra("position",0);
        type=intent.getStringExtra("photosType");
        views=new ArrayList<>();
        if (type.equals(Constant.SHOW_BIG_PHOTO_ADD_TOPIC)){
            photos.remove(photos.size()-1);
        }
        for (int i=0;i<photos.size();i++){
            ImageView imageView=new ImageView(ShowBigPhotosActivity.this);
            Object photo=photos.get(i);
            if (photo instanceof Bitmap){
                imageView.setImageBitmap((Bitmap) photo);
            }else {
                Glide.with(ShowBigPhotosActivity.this).load(photo).placeholder(R.drawable.loading).into(imageView);
            }
            views.add(imageView);
        }
        adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return photos.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView(views.get(position));
            }
        };

    }
}
