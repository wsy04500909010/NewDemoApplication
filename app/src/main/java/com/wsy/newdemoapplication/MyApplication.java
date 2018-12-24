package com.wsy.newdemoapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.wsy.newdemoapplication.dao.DaoMaster;
import com.wsy.newdemoapplication.dao.DaoSession;

/**
 * Created by WangSiYe on 2018/12/19.
 */
public class MyApplication extends Application {
    //静态单例
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
    }

}
