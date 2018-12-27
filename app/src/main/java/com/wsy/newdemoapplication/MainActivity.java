package com.wsy.newdemoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.ParcelableBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


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


        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("Hello" + i);
        }
        Observable<String> observable = Observable.fromIterable((Iterable<String>) list);

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("consumer1", s);
            }
        });

        Observable<Object> observable2 = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        });


        observable2.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                Log.e("consumer2", String.valueOf(s));
            }
        });


        Observable<Integer> observable3 = Observable.just("hello").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });
        observable3.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("consumer3", String.valueOf(integer));
            }
        });
//
//
//
//        Observable<Long> observable4 = Observable.interval(2, TimeUnit.SECONDS);
//        Observer<Long> observer = new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                System.out.println(aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        SystemClock.sleep(10000);//睡眠10秒后，才进行订阅  仍然从0开始，表示Observable内部逻辑刚开始执行
//        observable4.subscribe(observer);


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
