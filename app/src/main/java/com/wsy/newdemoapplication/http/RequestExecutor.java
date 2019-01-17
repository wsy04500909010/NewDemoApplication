package com.wsy.newdemoapplication.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public enum RequestExecutor {

    //使用枚举方法 保证全局唯一性
    INSTANCE;

    private ExecutorService executorService;

    RequestExecutor() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void execute(Request request,HttpListener httpListener) {
        executorService.execute(new RequestTask(request,httpListener));
    }
}
