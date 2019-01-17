package com.wsy.newdemoapplication.http;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class Message implements Runnable {

    private Response response;
    private HttpListener httpListener;


    public Message(Response response,HttpListener httpListener) {
        this.response = response;
        this.httpListener = httpListener;
    }

    @Override
    public void run() {
        //这里被回调到主线程
        if (response.getException()!=null){
            httpListener.failed(response.getException());
        }else {
            httpListener.successed(response);
        }

    }
}
