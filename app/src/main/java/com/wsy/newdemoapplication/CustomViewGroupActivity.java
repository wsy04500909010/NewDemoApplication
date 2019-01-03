package com.wsy.newdemoapplication;


import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.view.AutoWrapLayout;

import butterknife.BindView;

/**
 * Created by WSY on 2018/10/28.
 */
public class CustomViewGroupActivity extends BaseActivity {

    @BindView(R.id.autolayout)
    AutoWrapLayout autoWrapLayout;

    @Override
    protected void init() {
        TextView tv = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setText("手动添加的一项 这项应该很长很长了吧");
        tv.setLayoutParams(params);

        tv.setBackgroundColor(Color.parseColor("#FFFF0000"));
        tv.setTextSize(20);

        autoWrapLayout.addView(tv);
    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_customviewgroup);
    }
}
