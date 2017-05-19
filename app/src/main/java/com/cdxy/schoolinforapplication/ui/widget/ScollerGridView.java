package com.cdxy.schoolinforapplication.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by huihui on 2017/3/22.
 */

public class ScollerGridView extends GridView {
    public ScollerGridView(Context context) {
        super(context);
    }

    public ScollerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScollerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
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
