package com.wsy.newdemoapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by WSY on 2018/10/25.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView();
        ButterKnife.bind(this);

        init();

    }

    protected abstract void init();

    public abstract void setCustomView();
}
