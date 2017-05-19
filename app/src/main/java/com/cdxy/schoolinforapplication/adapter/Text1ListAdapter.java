package com.cdxy.schoolinforapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;

import java.util.List;

/**
 * Created by huihui on 2016/12/14.
 */

public class Text1ListAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public Text1ListAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_text_1,null);
            viewHolder=new ViewHolder();
            viewHolder.mTextView= (TextView) view.findViewById(R.id.text1);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        String text= (String) getItem(i);
        viewHolder.mTextView.setText(text);
        return view;
    }
    private class ViewHolder{
        private TextView mTextView;
    }
}
