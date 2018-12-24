package com.wsy.newdemoapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wsy.newdemoapplication.R;


/**
 * Created by WSY on 2018/10/25.
 */
public class MyCircle extends View {

    Paint mPaint1,mPaint2;
    int mColor;

    public MyCircle(Context context) {
        super(context);
        init();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context,attrs,0);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyCircle);
        mColor = a.getColor(R.styleable.MyCircle_circle_color,Color.GREEN);

        a.recycle();
        init();
    }

    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStrokeWidth(5f);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(mColor);
        mPaint2.setStrokeWidth(5f);
        mPaint2.setStyle(Paint.Style.FILL);

    }

    @Override
    //这个方法不重写会导致wrap_content 效果=match_parent
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
// 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
// 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = 100;
        int mHeight = 100;

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //不加这段代码  padding不起效
        int paddingleft = getPaddingLeft();
        int paddingtop = getPaddingTop();
        int paddingright = getPaddingRight();
        int paddingbottom = getPaddingBottom();


        int width = getWidth() - paddingleft - paddingright;
        int height = getHeight() - paddingtop - paddingbottom;

        int r = Math.min(width, height) / 2;
        // 画出圆（蓝色）
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(width / 2, height / 2, r, mPaint1);
        canvas.drawCircle(paddingleft+width / 2, paddingtop+height / 2, r, mPaint2);

    }
}
