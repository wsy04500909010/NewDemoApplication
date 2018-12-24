package com.wsy.newdemoapplication.dao;

/**
 * Created by WangSiYe on 2018/12/19.
 */
public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static GreenDaoManager mInstance;//单例

    private GreenDaoManager() {
        if (mInstance == null) {
//            DaoMaster.DevOpenHelper devOpenHelper = new
//                    DaoMaster.DevOpenHelper(MyApplication.getContext(), "database_name", null);//此处openhelper为自动生成开发所使用，发布版本需自定义
            MySQLiteOpenHelper devOpenHelper = new
                    MySQLiteOpenHelper(new GreenDaoContext(), "test_greendao.db", null);//GreenDaoContext为创建数据库路径使用
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {//保证异步处理安全操作
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

}
