package com.cdxy.schoolinforapplication.adapter.topic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2017/1/1.
 */

public class TopicPhotosAdapter extends BaseAdapter {
    private Activity activity;
    private List<Object> list;

    public TopicPhotosAdapter(Activity activity, List<Object> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_show_photo, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        for (Object photo:list){
            Object o=photo;
        }
        Object photo = getItem(i);
        if (photo instanceof Integer) {
            if ((int) photo == R.drawable.remind_add_photo) {
                viewHolder.imgPhoto.setImageDrawable(activity.getResources().getDrawable(R.drawable.remind_add_photo));
            } else {
                viewHolder.imgPhoto.setImageResource((int) photo);
            }

        } else {
            //通过照相获取的图片
            if (photo instanceof Bitmap)
                viewHolder.imgPhoto.setImageBitmap((Bitmap) photo);

            //从相册获取的图片
            if (photo instanceof String)
                Glide.with(activity).load(photo).placeholder(R.drawable.loading).into(viewHolder.imgPhoto);
        }
        viewHolder.imgDeletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                TopicPhotosAdapter.this.notifyDataSetChanged();
                viewHolder.imgDeletePhoto.setVisibility(View.GONE);
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.img_delete_photo)
        ImageView imgDeletePhoto;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
