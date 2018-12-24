package com.wsy.newdemoapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class PagerOnlyAdapter extends PagerAdapter {
    
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
