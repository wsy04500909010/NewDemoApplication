package com.wsy.newdemoapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WSY on 2018/10/28.
 */
public class AutoWrapLayout extends ViewGroup {


    int height = 0;

    public AutoWrapLayout(Context context) {
        super(context);
    }

    public AutoWrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //没有这个定义  marging属性不起效
        MarginLayoutParams params = null;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        }
//        else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
//            View childOne = getChildAt(0);
//
//            params = (MarginLayoutParams) childOne.getLayoutParams();
//
//            int childWidth = childOne.getMeasuredWidth();
//            int childHeight = childOne.getMeasuredHeight();
//            setMeasuredDimension(childWidth + params.leftMargin + params.rightMargin,
//                    childHeight * getChildCount() + params.topMargin + params.bottomMargin);
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            View childOne = getChildAt(0);
//            params = (MarginLayoutParams) childOne.getLayoutParams();
//            int childwidth = childOne.getMeasuredWidth();
//            setMeasuredDimension(childwidth + params.leftMargin + params.rightMargin, heightSize);
//        }
        else if (heightMode == MeasureSpec.AT_MOST) {

            int height = 0;
            int layoutWidth = 0; // 容器已经占据的宽度
            int childMeasureWidth = 0;

            View childView1 = getChildAt(0);
            height += childView1.getMeasuredHeight();
            layoutWidth += childView1.getMeasuredWidth();

            for (int i = 1; i < getChildCount(); i++) {
                View childView = getChildAt(i);
                childMeasureWidth = childView.getMeasuredWidth();

                if (layoutWidth + childMeasureWidth <= widthSize) {
                    layoutWidth += childMeasureWidth;
                } else {
                    layoutWidth = childMeasureWidth;//新起一行
                    height += childView.getMeasuredHeight();
                }
            }


//            View childOne = getChildAt(0);
//            params = (MarginLayoutParams) childOne.getLayoutParams();
//            int childheight = childOne.getMeasuredHeight();
            setMeasuredDimension(widthSize, height);
        }


//
//        if (height == 0) {
//            for (int i = 0; i < getChildCount(); i++) {
//                View childView = getChildAt(i);
//
//                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
//
//                childMeasureWidth = childView.getMeasuredWidth();
//
//                if (layoutWidth < getWidth()) {//如果一行没有排满，继续往右排列
//                    layoutWidth += childMeasureWidth;
//                } else {
//
//                    layoutWidth = 0;
//                    Log.e("childView", "=" + childView.getMeasuredHeight());
//                    height += childView.getMeasuredHeight();
//                }
//            }
//            Log.e("height", "=" + height);
//
//        }
//        setMeasuredDimension(widthMeasureSpec, height);


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

            if (layoutWidth + childMeasureWidth <= getWidth()) {//如果一行没有排满，继续往右排列
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
