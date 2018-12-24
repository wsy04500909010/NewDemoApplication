package com.wsy.newdemoapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.wsy.newdemoapplication.adapter.PagerOnlyAdapter;
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
    @BindView(R.id.btn_viewpager)
    Button btn_viewpager;
    @BindView(R.id.btn_fragmentviewpager)
    Button btn_fragmentviewpager;

    ViewPagerAdapter viewPagerAdapter;
    PagerOnlyAdapter pagerOnlyAdapter;
    List<Fragment> fragments;

    @Override
    protected void init() {

        initDefault();

        btn_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDefault();
            }
        });

        btn_fragmentviewpager.setOnClickListener((v) -> {
            initPageOnly();
        });

    }

    private void initDefault() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            MyFragment fragment = new MyFragment();
            fragments.add(fragment);
        }

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initPageOnly() {

        List<String> datas = new ArrayList<>();
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("5");
        pagerOnlyAdapter = new PagerOnlyAdapter(datas,ViewPagersActivity.this);
        viewPager.setAdapter(pagerOnlyAdapter);
    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_viewpagers);
    }
}
