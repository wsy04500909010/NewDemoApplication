package com.wsy.newdemoapplication.http;


import android.os.Handler;
import android.os.Looper;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class PosterHandler extends Handler {

    private static PosterHandler instance;

    public static PosterHandler getInstance() {
        if (instance == null) {
            synchronized (PosterHandler.class) {
                if (instance == null) {
                    instance = new PosterHandler();
                }
            }
        }
        return instance;
    }

    /**
     * 这个handler类是为了在子线程把消息发送到主线程  所以里面的looper要用mainLooper
     *
     */
    private PosterHandler() {
        super(Looper.getMainLooper());
    }

}
