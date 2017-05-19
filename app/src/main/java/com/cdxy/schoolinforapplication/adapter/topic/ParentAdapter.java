package com.cdxy.schoolinforapplication.adapter.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.model.tree.ChildEntity;
import com.cdxy.schoolinforapplication.model.tree.ParentEntity;

import java.util.List;

/**
 * Created by huihui on 2017/3/8.
 */

public class ParentAdapter extends BaseExpandableListAdapter {
    private List<ParentEntity> list;
    private Context context;
    private CheckBox checkBox;
    private TextView textView;
    private CheckBox childCheckBox;
    private TextView childTextView;

    public ParentAdapter(List<ParentEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list == null ? -1 : list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getChildren() == null ? -1 : list.get(i).getChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_tree_parent, null);
        checkBox = (CheckBox) view.findViewById(R.id.ck_parent);
        textView = (TextView) view.findViewById(R.id.txt_parent_name);
        final ParentEntity entity = (ParentEntity) getGroup(i);
        textView.setText(entity.getName());
        textView.setTextColor(entity.getColor());
        if (entity.isSelect()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    setChildenStutus(entity, true);
                } else {
                    setChildenStutus(entity, false);
                }
                ParentAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_tree_child, null);
        childCheckBox = (CheckBox) view.findViewById(R.id.ck_child);
        childTextView = (TextView) view.findViewById(R.id.txt_child_name);
        final ChildEntity entity = (ChildEntity) getChild(i, i1);
        childTextView.setText(entity.getName());
        childTextView.setTextColor(entity.getColor());
        if (entity.isSelect()) {
            childCheckBox.setChecked(true);
        } else {
            childCheckBox.setChecked(false);
        }
        childCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ParentEntity parentEntity= (ParentEntity) getGroup(i);
                List<ChildEntity> childEntities=parentEntity.getChildren();
                if (b) {
                    entity.setSelect(true);
                    int x = 0;//
                    for (int j=0;j<childEntities.size();j++){
                        if (childEntities.get(j).isSelect()){
                            x++;
                        }
                    }
                    if (x==childEntities.size()){
                        parentEntity.setSelect(true);
                    }
                } else {
                    entity.setSelect(false);
                    parentEntity.setSelect(false);
                }
                ParentAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    private void setChildenStutus(ParentEntity parentEntity, boolean isSelect) {
        parentEntity.setSelect(isSelect);
        List<ChildEntity> childEntities = parentEntity.getChildren();
        for (int j = 0; j < childEntities.size(); j++) {
            childEntities.get(j).setSelect(isSelect);
        }
    }
}
