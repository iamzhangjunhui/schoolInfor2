package com.cdxy.schoolinforapplication.adapter.message;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.message.SeeMeaaseStudentEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huihui on 2016/12/27.
 */

public class SeeMessageStudentAdapter extends BaseAdapter {
    private Context context;
    private List<SeeMeaaseStudentEntity> list;

    public SeeMessageStudentAdapter(Context context, List<SeeMeaaseStudentEntity> list) {
        this.context = context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_see_message_student, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        SeeMeaaseStudentEntity entity= (SeeMeaaseStudentEntity) getItem(i);
        String name=entity.getName();
        if (!TextUtils.isEmpty(name))
            viewHolder.txtSeeMessageStudentName.setText(name);
        String id=entity.getXuehao();
        if (!TextUtils.isEmpty(id))
            viewHolder.txtSeeMessageStudentId.setText(id);
        String department=entity.getXibie();
        if (!TextUtils.isEmpty(department))
            viewHolder.txtSeeMessageStudentDepartment.setText(department);
        String clazz=entity.getBanji();
        if (!TextUtils.isEmpty(clazz))
        viewHolder.txtSeeMessageStudentClazz.setText(clazz);
        String acceptTime=entity.getQueshi();
        if (!TextUtils.isEmpty(acceptTime))
            viewHolder.txtSeeMessageAcceptTime.setText(acceptTime);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.txt_see_message_student_id)
        TextView txtSeeMessageStudentId;
        @BindView(R.id.txt_see_message_student_name)
        TextView txtSeeMessageStudentName;
        @BindView(R.id.txt_see_message_student_department)
        TextView txtSeeMessageStudentDepartment;
        @BindView(R.id.txt_see_message_student_clazz)
        TextView txtSeeMessageStudentClazz;
        @BindView(R.id.txt_see_message_accept_time)
        TextView txtSeeMessageAcceptTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
