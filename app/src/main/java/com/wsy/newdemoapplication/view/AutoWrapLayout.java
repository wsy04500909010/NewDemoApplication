package com.wsy.newdemoapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WSY on 2018/10/28.
 */
public class AutoWrapLayout extends ViewGroup {


    public AutoWrapLayout(Context context) {
        super(context);
    }

    public AutoWrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;

        int layoutWidth = 0; // 容器已经占据的宽度
        int layoutHeight = 0;// 容器已经占据的高度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();

            if (layoutWidth < getWidth()) {//如果一行没有排满，继续往右排列
                l = layoutWidth;
                r = l + childMeasureWidth;
                t = layoutHeight;
                b = t + childMeasureHeight;
            } else { //排满后换行
                layoutWidth = 0;
                layoutHeight += maxChildHeight;
                maxChildHeight = 0;

                l = layoutWidth;
                r = l + childMeasureWidth;
                t = layoutHeight;
                b = t + childMeasureHeight;
            }

            layoutWidth += childMeasureWidth;//宽度累加
            if (childMeasureHeight > maxChildHeight) {
                maxChildHeight = childMeasureHeight;//每次比较 更新最大高度
            }

            child.layout(l, t, r, b);

        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoWrapLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new AutoWrapLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new AutoWrapLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
}
