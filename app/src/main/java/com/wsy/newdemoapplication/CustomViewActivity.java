package com.wsy.newdemoapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.view.PercentRound;

import butterknife.BindView;

/**
 * Created by WSY on 2018/10/25.
 */
public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.btn_rotate)
    Button btn_rotate;
    @BindView(R.id.btn_translation)
    Button btn_translation;
    @BindView(R.id.btn_scale)
    Button btn_scale;
    @BindView(R.id.btn_alpha)
    Button btn_alpha;
    @BindView(R.id.btn_animation_set)
    Button btn_animation_set;
    @BindView(R.id.btn_animation_bujian)
    Button btn_animation_bujian;
    @BindView(R.id.percentView)
    PercentRound percentView;

    @Override
    protected void init() {
//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 5f, 3f, 10f);
//        anim.setDuration(5000);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float currentValue = (float) animation.getAnimatedValue();
//                Log.d("TAG", "cuurent value is " + currentValue);
//            }
//        });
//        anim.setRepeatCount(3);
//        anim.setRepeatMode(ValueAnimator.REVERSE);
//        anim.start();

        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(percentView, "rotation", 0f, 360f);
                animator.setDuration(5000);
                animator.start();
            }
        });
        btn_translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float curTranslationX = percentView.getTranslationX();
                ObjectAnimator animator = ObjectAnimator.ofFloat(percentView, "translationX", curTranslationX, 360f, curTranslationX);
                animator.setDuration(5000);
                animator.start();
            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(percentView, "scaleY", 1f, 3f, 1f);
                animator.setDuration(5000);
                animator.start();
            }
        });
        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(percentView, "alpha", 1f, 0f, 1f);
                animator.setDuration(5000);
                animator.start();
            }
        });
        btn_animation_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //代码模式
                ObjectAnimator alpha = ObjectAnimator.ofFloat(percentView, "alpha", 1f, 0f, 1f);
                float curTranslationX = percentView.getTranslationX();
                ObjectAnimator transx = ObjectAnimator.ofFloat(percentView, "translationX", curTranslationX, 360f, curTranslationX);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(percentView, "rotation", 0f, 360f);

                AnimatorSet set = new AnimatorSet();
                set.play(alpha).with(transx).after(rotate);
                set.setDuration(5000);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.d("anim", "动画执行完咯");
                    }
                });
                set.start();

                //xml加载模式
//                Animator animator = AnimatorInflater.loadAnimator(CustomViewActivity.this, R.animator.zuhe);
//                animator.setTarget(percentView);
//                animator.start();
            }
        });


        percentView.setCurrentCount(25);



        //补间组合动画
        final Animation translateAnimation = AnimationUtils.loadAnimation(CustomViewActivity.this, R.anim.view_animation_bujian);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btn_animation_bujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
                btn_animation_bujian.startAnimation(translateAnimation);

            }
        });



    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_customview);
    }


}
