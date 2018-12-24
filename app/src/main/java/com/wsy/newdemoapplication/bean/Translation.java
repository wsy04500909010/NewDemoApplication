package com.wsy.newdemoapplication.bean;

import java.io.Serializable;

/**
 * Created by WSY on 2018/6/18.
 * 金山词霸api返回 实体bean
 */

public class Translation implements Serializable {
    private int status;

    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }

}
