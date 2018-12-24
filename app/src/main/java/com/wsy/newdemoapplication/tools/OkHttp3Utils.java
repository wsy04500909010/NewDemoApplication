package com.wsy.newdemoapplication.tools;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by WSY on 2018/6/18.
 */

public class OkHttp3Utils {

    private static OkHttpClient okHttpClient = null;
    private static final int DEFAULT_TIMEOUT = 30;
    private static Context mContext;

    public static OkHttpClient getOkHttpSingletonInstance() {
        okHttpClient = new OkHttpClient();
        //设置合理的超时
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) //设置连接超时 30秒
                .writeTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(new LoggingInterceptor())//添加请求拦截
                .retryOnConnectionFailure(true);

        //如果不是在正式包，添加拦截 打印响应json
//                    if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (TextUtils.isEmpty(message)) return;
                        String s = message.substring(0, 1);
                        //如果收到响应是json才打印
                        if ("{".equals(s) || "[".equals(s)) {
                            Log.i("backjson", "收到响应: " + message);
                        }
                    }
                });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpBuilder.addInterceptor(logging);
//                    }
        okHttpClient = httpBuilder.build();
        return okHttpClient;
    }
}
