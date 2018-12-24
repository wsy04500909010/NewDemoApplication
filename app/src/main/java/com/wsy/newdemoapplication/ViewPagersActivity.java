package com.wsy.newdemoapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wsy.newdemoapplication.adapter.ViewPagerAdapter;
import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class ViewPagersActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;
    List<Fragment> fragments;

    @Override
    protected void init() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            MyFragment fragment = new MyFragment();
            fragments.add(fragment);
        }

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);


    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_viewpagers);
    }
}
