package com.wsy.newdemoapplication.bean;

import android.util.Log;

import com.wsy.newdemoapplication.interf.Dog;

/**
 * Created by WSY on 2018/11/2.
 */
public class MyDog implements Dog {
    @Override
    public void call() {
        Log.e("MyDog--Call", "wangwang");
    }

    @Override
    public void eat() {
        Log.e("MyDog--Eat", "吃东西");
    }
}
