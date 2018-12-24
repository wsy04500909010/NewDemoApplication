package com.wsy.newdemoapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wsy.newdemoapplication.R;

import java.text.DecimalFormat;


/**
 * Created by WSY on 2018/10/25.  百分比圆环
 */
public class PercentRound extends View {

    int outColor, inColor, textColor, smalltextColor;
    String text;
    float textSize;
    String smalltext;
    float smalltextSize;
    int startAngle, sweepAngle;

    int mCircleXY;
    float mRadius;
    RectF mRectF;

    Paint outPaint, inPaint, textPaint;
    Paint smallTextPaint;
    int length;
    int width, height;

    private float currentAngle = 0;
    DecimalFormat df = new DecimalFormat("0.0");//格式化小数，不足的补0
    //小原点的半径
    private int DEFAULT_LITLE_WIDTH = dipToPx(10);
    private ValueAnimator progressAnimator;
    private float lastAngle = 180;//最后绘制的角度
    private int aniSpeed = 5000;//设置滚动的速度
    private float maxCount = 50;


    public PercentRound(Context context) {
        super(context);
    }

    public PercentRound(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        this(context,attrs,0);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PercentRound);
        if (a != null) {
            outColor = a.getColor(R.styleable.PercentRound_out_color, Color.GREEN);
            inColor = a.getColor(R.styleable.PercentRound_in_color, Color.BLUE);
            text = a.getString(R.styleable.PercentRound_text);
            textColor = a.getColor(R.styleable.PercentRound_textColor, Color.BLACK);
            textSize = a.getDimension(R.styleable.PercentRound_textSize, 16f);
            smalltext = a.getString(R.styleable.PercentRound_small_text);
            smalltextColor = a.getColor(R.styleable.PercentRound_small_textColor, Color.BLACK);
            smalltextSize = a.getDimension(R.styleable.PercentRound_small_textSize, 12f);
            startAngle = a.getInt(R.styleable.PercentRound_startAngle, -90);
            sweepAngle = a.getInt(R.styleable.PercentRound_sweepAngle, 230);

            a.recycle();
        }
        init();

    }

    public PercentRound(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private void init() {

        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        outPaint.setColor(outColor);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeCap(Paint.Cap.ROUND);
        outPaint.setStrokeWidth(20);


        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inPaint.setColor(inColor);
        inPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.STROKE);

        smallTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        smallTextPaint.setColor(textColor);
        smallTextPaint.setTextSize(textSize);
        smallTextPaint.setTextAlign(Paint.Align.CENTER);
        smallTextPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultWidthResult = 300;
        int defaultHeightResult = 300;


        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidthResult, defaultHeightResult);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(defaultWidthResult, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, defaultHeightResult);
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

        width = getWidth() - paddingleft - paddingright;
        height = getHeight() - paddingtop - paddingbottom;

        length = Math.min(width, height);
        mCircleXY = length / 2;//画圆的 圆心 横纵坐标
        mRadius = length * 0.5f / 2;//半径

        mRectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f, length * 0.9f);


        int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.GREEN};
        SweepGradient sg = new SweepGradient(mCircleXY, mCircleXY, colors, null);
        outPaint.setShader(sg);

        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, inPaint);
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, outPaint);
        canvas.drawText(text, 0, text.length(), mCircleXY, mCircleXY + textSize / 4, textPaint);
        canvas.drawText(smalltext, 0, smalltext.length(), mCircleXY + mCircleXY / 3, mCircleXY - mCircleXY / 15, smallTextPaint);

//        canvas.drawPosText("巴扎黑", new float[]{100, 100, 100, 200, 100, 300}, textPaint);

        canvas.translate(mCircleXY, mCircleXY);//这时候的画布已经移动到了中心位置
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);//设置填充样式
        paintCircle.setAntiAlias(true);//抗锯齿功能
        paintCircle.setColor(Color.WHITE);

        /**
         * 第一个参数为正则顺时针，否则逆时针
         * 后面两个参数是圆心
         * 画布的旋转一定要在，画图形之前进行旋转
         */
        canvas.rotate(currentAngle);
        canvas.drawCircle(-0.4f*length, 0, DEFAULT_LITLE_WIDTH, paintCircle);
        canvas.rotate(-currentAngle);


    }

    public void setCurrentCount(float currentCounts) {
//        this.currentCount = currentCounts > maxCount ? maxCount : currentCounts;
        float curren = Float.parseFloat(df.format(currentCounts / maxCount));//返回的是String类型的
        float last_angle = 180 * (curren > 1 ? 1 : curren);//最后要到达的角度 超过180 就按180处理 否则按实际值相对180占比处理
        lastAngle = currentAngle;//保存最后绘制的位置 初始值为0
        setAnimation(lastAngle, last_angle, aniSpeed);
    }

    /**
     * 为进度设置动画
     *
     * @param last
     * @param end
     */
    private void setAnimation(float last, float end, int length) {
        progressAnimator = ValueAnimator.ofFloat(last, end);
        Log.e("last=====", last + "");
        Log.e("current=====", end + "");
        progressAnimator.setDuration(length);
//        progressAnimator.setTarget(currentAngle);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        progressAnimator.start();
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
