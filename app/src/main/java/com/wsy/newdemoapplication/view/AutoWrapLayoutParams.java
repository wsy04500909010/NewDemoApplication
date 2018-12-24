package com.wsy.newdemoapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.wsy.newdemoapplication.R;


/**
 * Created by WSY on 2018/10/28.
 */
public class AutoWrapLayoutParams extends ViewGroup.MarginLayoutParams {

    public static final int POSITION_MIDDLE = 0; // 中间
    public static final int POSITION_LEFT = 1; // 左上方
    public static final int POSITION_RIGHT = 2; // 右上方
    public static final int POSITION_BOTTOM = 3; // 左下角
    public static final int POSITION_RIGHTANDBOTTOM = 4; // 右下角

    public int position = POSITION_LEFT;  // 默认我们的位置就是左上角


    public AutoWrapLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.AutoWrapLayout);

        position = a.getInt(R.styleable.AutoWrapLayout_layout_position,position);
        a.recycle();

    }

    public AutoWrapLayoutParams(int width, int height) {
        super(width, height);
    }

    public AutoWrapLayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }
}
