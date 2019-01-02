package com.wsy.newdemoapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.util.DensityUtil;

import butterknife.BindView;

import static java.security.AccessController.getContext;

/**
 * Created by WangSiYe on 2019/1/2.
 */
public class AnimationActivity extends BaseActivity {

    @BindView(R.id.btn_animation)
    Button btn_animation;
    @BindView(R.id.btn_hide)
    Button btn_hide;
    @BindView(R.id.image_search)
    ImageView image_search;

    private Animation mSearchAnimation;

    @Override
    protected void init() {
        initAnimation();

        image_search.setVisibility(View.GONE);

        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_search.setVisibility(View.INVISIBLE);
//                image_search.bringToFront();
//                image_search.requestLayout();
                image_search.startAnimation(mSearchAnimation);
            }
        });
        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_search.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_animation);
    }

    private void initAnimation() {
        float fromXValue = DensityUtil.dip2px(this, -30f);
        float fromYValue = DensityUtil.dip2px(this, 19f);
        mSearchAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, fromXValue, Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, fromYValue, Animation.RELATIVE_TO_SELF, 0f);
        mSearchAnimation.setDuration(2000);
        mSearchAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                image_search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
