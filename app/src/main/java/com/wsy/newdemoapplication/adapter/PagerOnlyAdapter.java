package com.wsy.newdemoapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsy.newdemoapplication.R;

import java.util.List;

/**
 * Created by WangSiYe on 2018/12/24.
 */
public class PagerOnlyAdapter extends PagerAdapter {

    List<String> titles;
    Context context;


    public PagerOnlyAdapter(List<String> titles, Context context) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_viewpager, null);
        //在这里可以做相应的操作
        TextView tv = view.findViewById(R.id.tv);
        //数据填充
        tv.setText(titles.get(position));

        container.addView(view);    //这一步很重要

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
