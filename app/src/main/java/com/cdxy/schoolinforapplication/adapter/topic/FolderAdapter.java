package com.cdxy.schoolinforapplication.adapter.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.topic.Folder;

import java.util.List;

/**
 * Created by PC on 2017/2/17.
 */

public class FolderAdapter extends BaseAdapter {
    private Context context;
    private List<Folder> folders;

    public FolderAdapter(Context context, List<Folder> folders) {
        this.context = context;
        this.folders = folders;
    }

    @Override
    public int getCount() {
        return folders==null?-1:folders.size();
    }

    @Override
    public Object getItem(int position) {
        return folders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_folder,null);
            viewHolder=new ViewHolder();
            viewHolder.mTxtFolderName= (TextView) view.findViewById(R.id.txt_folder_name);
            viewHolder.mTxtFolderPath= (TextView) view.findViewById(R.id.txt_folder_path);
            viewHolder.mTxtFolderSize= (TextView) view.findViewById(R.id.txt_folder_size);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Folder folder= (Folder) getItem(position);
        viewHolder.mTxtFolderSize.setText(folder.getSelectPhotos().size()+"张图片");
        viewHolder.mTxtFolderPath.setText(folder.getPath());
        viewHolder.mTxtFolderName.setText(folder.getName());
        return view;
    }
    class ViewHolder{
        private TextView mTxtFolderName;
        private TextView mTxtFolderPath;
        private TextView mTxtFolderSize;

    }
}
