package com.wsy.newdemoapplication;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.scanapps.ApkTool;
import com.wsy.newdemoapplication.scanapps.AppsAdapter;
import com.wsy.newdemoapplication.scanapps.MyAppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WangSiYe on 2019/1/21.
 */
public class AppsActivity extends BaseActivity implements AppsAdapter.OnItemClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;

    AppsAdapter appsAdapter;
    List<MyAppInfo> myAppInfoList;

    Handler mHandler = new Handler();

    @Override
    protected void init() {

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        myAppInfoList = new ArrayList<>();
        appsAdapter = new AppsAdapter(myAppInfoList);
        appsAdapter.setOnItemClickListener(this);


        rv.setAdapter(appsAdapter);



        initData();

    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //扫描得到APP列表
                final List<MyAppInfo> appInfos = ApkTool.scanLocalInstallAppList(AppsActivity.this.getPackageManager());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        myAppInfoList.clear();
//                        myAppInfoList.addAll(appInfos);
//                        appsAdapter.notifyDataSetChanged();

                        appsAdapter.setData(appInfos);
                    }
                });
            }
        }.start();
    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_apps);

    }

    @Override
    public void onItemClick(View v) {

    }
}
