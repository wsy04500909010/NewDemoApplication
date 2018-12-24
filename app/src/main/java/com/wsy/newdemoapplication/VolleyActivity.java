package com.wsy.newdemoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wsy.newdemoapplication.bean.VolleyTestBean;
import com.wsy.newdemoapplication.volley.GsonRequest;
import com.wsy.newdemoapplication.volley.MyErrorListener;
import com.wsy.newdemoapplication.volley.MyReponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WSY on 2018/7/5.
 */

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_volleyget, btn_volleypost, btn_gsonget, btn_gsonpost;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        btn_volleyget = (Button) findViewById(R.id.btn_volleyget);
        btn_volleypost = (Button) findViewById(R.id.btn_volleypost);
        btn_gsonget = (Button) findViewById(R.id.btn_gsonrequest_get);
        btn_gsonpost = (Button) findViewById(R.id.btn_gsonrequest_post);


        btn_volleyget.setOnClickListener(this);
        btn_volleypost.setOnClickListener(this);
        btn_gsonget.setOnClickListener(this);
        btn_gsonpost.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_volleyget:
                volleyget();
                break;
            case R.id.btn_volleypost:
                volleypost();
                break;
            case R.id.btn_gsonrequest_get:
                gsonget();
                break;
            case R.id.btn_gsonrequest_post:
                gsonpost();
                break;
        }
    }

    private void gsonpost() {
        String url = "http://api.k780.com:88/?app=phone.get";
        RequestQueue queue = Volley.newRequestQueue(this);
        Map<String, String> map = new HashMap<>();
        map.put("phone", "13800138000");
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");

        GsonRequest<VolleyTestBean> request = new GsonRequest<VolleyTestBean>(url, map, VolleyTestBean.class, new MyReponseListener<VolleyTestBean>() {
            @Override
            public boolean onMyResponse(VolleyTestBean volleyTestBean) {

                if (volleyTestBean.getResult().getArea() != null) {

                    Log.e("gsonpost", volleyTestBean.getResult().getArea());
                    return true;
                } else {
                    return false;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    private void gsonget() {
        String url = "http://api.k780.com:88/?app=idcard.get&idcard=110101199001011114&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        RequestQueue queue = Volley.newRequestQueue(this);

        GsonRequest<VolleyTestBean> request = new GsonRequest(url, VolleyTestBean.class, new MyReponseListener<VolleyTestBean>() {
            @Override
            public void onResponse(VolleyTestBean response) {
                super.onResponse(response);

                Toast.makeText(VolleyActivity.this, response.getResult().getPhone(), Toast.LENGTH_SHORT).show();
                Log.e("gsonget", response.getResult().getAtt());

            }
        }, new MyErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        queue.add(request);

    }

    private void volleypost() {
        String url = "http://api.k780.com:88/?app=phone.get";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("success", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {//重写了 StringRequest 父类 Request的 getParams方法
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("phone", "13800138000");
                map.put("appkey", "10003");
                map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
                map.put("format", "json");
                map.put("idcard", "110101199001011114");
                return map;
            }
        };
        queue.add(request);
    }

    private void volleyget() {
        String url = "http://api.k780.com:88/?app=phone.get&phone=13800138000&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }
}
