package com.wsy.newdemoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.ParcelableBean;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MainActivity extends BaseActivity {

    public static String TAG;


    @BindView(R.id.btn_viewpagers)
    Button btn_viewpagers;
    @BindView(R.id.btn_okhttp)
    Button btn_okhttp;
    @BindView(R.id.btn_retrofit)
    Button btn_retrofit;
    @BindView(R.id.btn_volley)
    Button btn_volley;
    @BindView(R.id.btn_viewstub)
    Button btn_viewstub;
    @BindView(R.id.btn_customview)
    Button btn_customview;
    @BindView(R.id.btn_customviewgroup)
    Button btn_customviewgroup;
    @BindView(R.id.btn_sqlite)
    Button btn_sqlite;
    @BindView(R.id.btn_proxy)
    Button btn_proxy;
    @BindView(R.id.btn_kotlin)
    Button btn_kotlin;
    @BindView(R.id.btn_focus_gridview)
    Button btn_focus_gridview;
    @BindView(R.id.btn_greendao)
    Button btn_greendao;


    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void init() {
        //        ButterKnife.bind(this);
//        Util.getLog();


//        {
//            String cx = new String("22");
//        }
//
//        cx = "1";

        TAG = this.getClass().getSimpleName();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4);

            }
        }).subscribe(new Observer<Integer>() {

            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n");
            }
        });


//        btn_okhttp = (Button) findViewById(R.id.btn_okhttp);
//        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);
//        btn_volley = (Button) findViewById(R.id.btn_volley);
//        btn_viewstub = (Button) findViewById(R.id.btn_viewstub);

        btn_viewpagers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ViewPagersActivity.class);
                startActivity(intent1);
            }
        });
        btn_okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, OkhttpActivity.class);
                startActivity(intent1);
            }
        });
        btn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, RetrofitDemoActivity.class);
                startActivity(intent1);
            }
        });
        btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent1);
            }
        });
        btn_viewstub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ViewStubActivity.class);
                Bundle bundle = new Bundle();
                ParcelableBean parcelablebean = new ParcelableBean();
                parcelablebean.setName("你好");
                parcelablebean.setAge(11);
                bundle.putParcelable("parcebleBean", parcelablebean);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        btn_customview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intent1);
            }
        });
        btn_customviewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, CustomViewGroupActivity.class);
                startActivity(intent1);
            }
        });
        btn_sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SqliteActivity.class);
                startActivity(intent1);
            }
        });
        btn_proxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, ProxyActivity.class);
                startActivity(intent1);
            }
        });
        btn_focus_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, FocusGridviewActivity.class);
                startActivity(intent1);
            }
        });
        btn_greendao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, GreenDaoActivity.class);
                startActivity(intent1);
            }
        });
    }


}
