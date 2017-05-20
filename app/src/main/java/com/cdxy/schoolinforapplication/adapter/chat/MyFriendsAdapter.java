package com.cdxy.schoolinforapplication.adapter.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.chat.MyFriendEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;



public class MyFriendsAdapter extends BaseAdapter {
    private List<MyFriendEntity> list;
    private Context context;

    public MyFriendsAdapter(List<MyFriendEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? -1 : list.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_my_friend, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MyFriendEntity myFriendEntity = (MyFriendEntity) getItem(i);
        viewHolder.txtName.setText(myFriendEntity.getName());
        Glide.with(context).load(myFriendEntity.getIcon()).placeholder(R.drawable.loading).bitmapTransform(new CropCircleTransformation(context)).into(viewHolder.imgIcon);
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.txt_name)
        TextView txtName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
