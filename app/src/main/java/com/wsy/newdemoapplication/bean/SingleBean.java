package com.wsy.newdemoapplication.bean;

/**
 * Created by WSY on 2018/10/24.
 */
public class SingleBean {
    private SingleBean(){

    }
    public static SingleBean getInstance(){
        return InnerClass.a;
    }

    private static class InnerClass{

        private static final SingleBean a = new SingleBean();
    }
}
