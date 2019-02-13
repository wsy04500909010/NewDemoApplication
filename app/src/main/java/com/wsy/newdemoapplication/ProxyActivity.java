package com.wsy.newdemoapplication;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wsy.newdemoapplication.base.BaseActivity;
import com.wsy.newdemoapplication.bean.MyDog;
import com.wsy.newdemoapplication.interf.Dog;
import com.wsy.newdemoapplication.proxy.HookManager;
import com.wsy.newdemoapplication.proxy.ProxyDog;

import butterknife.BindView;

/**
 * Created by WSY on 2018/11/2.
 */
public class ProxyActivity extends BaseActivity {

    @BindView(R.id.btn_ceshi)
    Button btn_ceshi;
    @BindView(R.id.btn_lanjie)
    Button btn_lanjie;

    @Override
    protected void init() {
        btn_ceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dog dog1 = new MyDog();
                dog1.call();
                dog1.eat();
                Dog dog2 = (Dog) new ProxyDog(dog1).getProxyInstance();
                dog2.call();
                dog2.eat();

            }
        });

        btn_lanjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProxyActivity.this, "原始", Toast.LENGTH_SHORT).show();
            }
        });
        HookManager.hookOnClickListener(btn_lanjie);


    }

    @Override
    public void setCustomView() {
        setContentView(R.layout.activity_proxy);
    }
}
