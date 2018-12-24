package com.wsy.newdemoapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.db.MyDBHelper;

import butterknife.BindView;

/**
 * Created by WSY on 2018/10/30.
 */
public class SqliteActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_create_booktable)
    Button btn_create_booktable;
    @BindView(R.id.btn_create_catetable)
    Button btn_create_catetable;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.btn_check)
    Button btn_check;
    @BindView(R.id.btn_update)
    Button btn_update;
    @BindView(R.id.btn_delete)
    Button btn_delete;

    private MyDBHelper dbHelper;


    @Override
    protected void init() {

        btn_create_booktable.setOnClickListener(this);
        btn_create_catetable.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_sqlite);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create_booktable:
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,1);
                dbHelper.getReadableDatabase();

                break;
            case R.id.btn_create_catetable://通过升级数据库的方式  先删除已有的表 然后 添加新表
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,2);
                dbHelper.getReadableDatabase();
                break;
            case R.id.btn_add:
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,2);
                SQLiteDatabase sd1 = dbHelper.getReadableDatabase();

                //方法1
//                sd.execSQL("insert into book(id,author,price,pages,name) values(null,'作者', 4,55,'wsy')");
                //方法 2
                ContentValues cv = new ContentValues();
                cv.put("author","myself");
                cv.put("price",100.00);
                cv.put("pages",102);
                cv.put("name","new");

                sd1.insert("book",null,cv);
                sd1.close();

                break;
            case R.id.btn_check:
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,2);
                SQLiteDatabase sd2 = dbHelper.getReadableDatabase();

                Cursor cursor = sd2.query("book",new String[]{"id","author","price","pages","name"},"id=?",
                        new String[]{"2"},null,null,null);

                while (cursor.moveToNext()) {
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    String pages = cursor.getString(cursor.getColumnIndex("pages"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));

                    System.out.println("query------->" + "作者："+author+" "+"价格："+price+" "+"页数："+pages);
                }

                break;
            case R.id.btn_update:
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,2);
                SQLiteDatabase sd3 = dbHelper.getReadableDatabase();

                ContentValues cv2 = new ContentValues();
                cv2.put("price",500);
                cv2.put("name","jinpingmei");

                String whereClause = "id=?";
                String []whereArgs = {String.valueOf(2)};

                sd3.update("book",cv2,whereClause,whereArgs);


                break;
            case R.id.btn_delete:
                dbHelper = new MyDBHelper(SqliteActivity.this,"BookStore.db",null,2);
                SQLiteDatabase sd4 = dbHelper.getReadableDatabase();

                String whereClause2 = "id=?";
                String []whereArgs2 = {String.valueOf(1)};

                sd4.delete("book",whereClause2,whereArgs2);

                break;
        }
    }

}


