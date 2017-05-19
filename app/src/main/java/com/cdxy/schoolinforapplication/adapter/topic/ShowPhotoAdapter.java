package com.cdxy.schoolinforapplication.adapter.topic;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.util.Constant;

import java.util.List;

/**
 * Created by huihui on 2016/12/28.
 */

public class ShowPhotoAdapter extends RecyclerView.Adapter<ShowPhotoAdapter.ViewHolder> {

    private Activity activity;
    private List<Object> list;


    public ShowPhotoAdapter(Activity activity, List<Object> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_show_photo, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (list.get(position) instanceof Integer) {
            holder.imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.remind_add_photo));
            if ((int) list.get(position) == R.drawable.remind_add_photo) {
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_way, null);
                        TextView txtWay1 = (TextView) dialogView.findViewById(R.id.txt_way1);
                        TextView txtWay2 = (TextView) dialogView.findViewById(R.id.txt_way2);
                        txtWay1.setText("拍照");
                        txtWay2.setText("从相册中获取");
                        final AlertDialog dialog = new AlertDialog.Builder(activity).setView(dialogView).create();
                        dialog.show();
                        txtWay1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //打开系统拍照程序，选择拍照图片
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                                    Toast.makeText(activity,"拍照的权限申请失败",Toast.LENGTH_LONG).show();
                                    return;
                                }
                                activity.startActivityForResult(intent, Constant.REQUEST_CODE_CAMERA);
                                dialog.dismiss();
                            }
                        });
                        txtWay2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ////打开系统图库程序，选择图片
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                activity.startActivityForResult(intent, Constant.REQUEST_CODE_PICTURE);
                                dialog.dismiss();
                            }
                        });
                    }
                });
            }

        } else {
            //通过照相获取的图片
            if (list.get(position) instanceof Bitmap)
                holder.imageView.setImageBitmap((Bitmap) list.get(position));

            //从相册获取的图片
            if (list.get(position) instanceof String)
                Glide.with(activity).load(list.get(position)).placeholder(R.drawable.loading).into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView imgDeletePhoto;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.img_photo);
            imgDeletePhoto = (ImageView) view.findViewById(R.id.img_delete_photo);

        }
    }
}
