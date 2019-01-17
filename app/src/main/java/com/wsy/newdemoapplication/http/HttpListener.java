package com.wsy.newdemoapplication.http;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public interface HttpListener {

    void successed(Response response);

    void failed(Exception e);

}
