package com.wsy.newdemoapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by WSY on 2018/10/30.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    //这里的版本号 应该是在代码中写死声明 防止 不同方法操作的数据库版本不同从而报错,本项目数据库模块将数据库升级功能单独用一个按钮实现 所以版本号传进来
    public MyDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final String CREATE_BOOK = "create table book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    public static final String CREATE_CATEGORY = "create table category(" +
            "id integer primary key autoincrement," +
            "type text," +
            "number integer," +
            "title text)";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop table if exists Book");
//        sqLiteDatabase.execSQL("drop table if exists Category");
//        onCreate(sqLiteDatabase);

        Log.d("onUpdate", "数据库已从版本" + i + "升级到版本" + i1);

        String sqlStr1 = "alter table book add column duration integer";
        sqLiteDatabase.execSQL(sqlStr1);
    }
}
