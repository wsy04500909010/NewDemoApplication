package com.wsy.newdemoapplication.volley;

import com.android.volley.Response;


/**
 * Created by WSY on 2018/7/5.
 */

public abstract class MyReponseListener<T> implements Response.Listener<T> {
    @Override
    public void onResponse(T response) {
        onMyResponse(response);
    }

    public boolean onMyResponse(T t) {
        //      DialogMaker.closeProgressDialog();
        // 自定义处理逻辑
        return true;
    }
}
