package com.cdxy.schoolinforapplication.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by huihui on 2017/3/15.
 */

public class ScollerExpandableListView extends ExpandableListView {
    public ScollerExpandableListView(Context context) {
        super(context);
    }

    public ScollerExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScollerExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
