package com.wsy.newdemoapplication.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wsy.newdemoapplication.fragment.MyFragment;

import java.util.List;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> datas;


    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        datas = fragments;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", String.valueOf(position));
        f.setArguments(bundle);

        return f;
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
