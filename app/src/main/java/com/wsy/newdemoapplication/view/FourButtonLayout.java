package com.wsy.newdemoapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

/**
 * Created by WSY on 2018/10/28.
 */
public class FourButtonLayout extends ViewGroup {
    public FourButtonLayout(Context context) {
        super(context);
    }

    public FourButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //params = (MarginLayoutParams) childOne.getLayoutParams();
    // 如果不重写layoutParams相关的代码，这样直接转换会出现问题
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //没有这个定义  margin属性不起效
        MarginLayoutParams params = null;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //加上下面这段  viewgroup的wrap_content属性才有效
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);

            params = (MarginLayoutParams) childOne.getLayoutParams();

            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth + params.leftMargin + params.rightMargin,
                    childHeight * getChildCount() + params.topMargin + params.bottomMargin);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            params = (MarginLayoutParams) childOne.getLayoutParams();
            int childwidth = childOne.getMeasuredWidth();
            setMeasuredDimension(childwidth + params.leftMargin + params.rightMargin, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            params = (MarginLayoutParams) childOne.getLayoutParams();
            int childheight = childOne.getMeasuredHeight();
            setMeasuredDimension(widthSize, childheight + params.topMargin + params.bottomMargin);
        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = 0;
        int count = getChildCount();
        View child;
        Log.e("ri", count + "");
        child = getChildAt(0);
        MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
        int c1 = params.leftMargin;
        int c2 = params.topMargin;
        int c3 = c1 + child.getMeasuredWidth();
        int c4 = c2 + child.getMeasuredHeight();
        child.layout(c1, c2, c3, c4);

        height = c4 + params.bottomMargin;
        for (int i = 1; i < count; i++) {
            child = getChildAt(i);
            child.layout(0, height, child.getMeasuredWidth(), height + child.getMeasuredHeight());
            height += child.getMeasuredHeight();
        }
    }
}
