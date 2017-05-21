package com.cdxy.schoolinforapplication.adapter.topic;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.topic.SelectPhoto;

import java.io.File;
import java.util.List;

/**
 * Created by PC on 2017/2/16.
 */

public class SelectPhotoAdapter extends BaseAdapter {
    private Context context;
    private List<SelectPhoto> photos;
    private int height;

    public SelectPhotoAdapter(Context context, List<SelectPhoto> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos == null ? -1 : photos.size();

    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_select_photo, null);
            viewHolder = new ViewHolder();
            viewHolder.mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
            viewHolder.mImgPhoto = (ImageView) view.findViewById(R.id.img_file_photo);
            //动态改变要显示的图片控件的高宽
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point size = new Point();
                windowManager.getDefaultDisplay().getSize(size);
                height = size.x / 4;
            } else {
                height = windowManager.getDefaultDisplay().getWidth() / 4;
            }
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = height;
            viewHolder.mImgPhoto.setLayoutParams(params);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final SelectPhoto selectPhoto = (SelectPhoto) getItem(position);
        String path = selectPhoto.getPath();
        File file = new File(path);
        if (file.exists()) {
            Glide.with(context).load(file).placeholder(R.drawable.loading).into(viewHolder.mImgPhoto);
        }
        final int isSelect = selectPhoto.getIsSelect();
        if (isSelect == 0) {
            viewHolder.mCheckBox.setChecked(false);
        } else {
            viewHolder.mCheckBox.setChecked(true);
        }
        return view;
    }

    class ViewHolder {
        private ImageView mImgPhoto;
        private CheckBox mCheckBox;
    }
}
