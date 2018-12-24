package com.wsy.newdemoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by WSY on 2018/10/19.
 */
public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        btn1 = (Button) findViewById(R.id.btn_translation_ori);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_translation_ori:

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.baidu.com/")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        if(response.isSuccessful()){//回调的方法执行在子线程。
                            Log.d("kwwl","获取数据成功了");
                            Log.d("kwwl","response.code()=="+response.code());
                            Log.d("kwwl","response.body().string()=="+response.body().string());
                        }
                    }

                });

                break;
        }
    }
}
