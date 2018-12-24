package com.wsy.newdemoapplication.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by WSY on 2018/7/5.
 */

public class GsonRequest<T> extends Request<T> {

    private static final String TAG = "GsonRequest";
    //超时时间，默认10秒
    private int defaultHttpTimeOut = 10 * 1000;
    //回调监听
    private Response.Listener<T> listener;
    //返回类型
    private Type type;
    //请求参数
    private Map<String, String> methodBody;

    //Get
    public GsonRequest(String url, Type type, Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        super(Method.GET, url, errorlistener);

        // 不启用缓存(默认是true)
        setShouldCache(false);
        this.type = type;
        this.listener = listener;
        // 设置重连策略
        this.setRetryPolicy(new DefaultRetryPolicy(defaultHttpTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }


    //Post
    public GsonRequest(String url, Map<String, String> methodBody, Type type, Response.Listener<T> listener, Response.ErrorListener errorlistener) {
        super(Method.POST, url, errorlistener);

        // 不启用缓存(默认是true)
        setShouldCache(false);
        this.type = type;
        this.listener = listener;
        // 设置重连策略
        this.setRetryPolicy(new DefaultRetryPolicy(defaultHttpTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        this.methodBody = methodBody;


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (methodBody == null) {
            return super.getParams();
        }

        //创建一个集合，保存请求参数
        Map<String, String> map = new LinkedHashMap<>();
        //----此处可以添加多个通用(写死的每次都携带的)参数
        //map.put(key,value);
        //------
        //------
        //遍历集合
        Iterator<Map.Entry<String, String>> iter = methodBody.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            // 获取返回的数据(在 Content-Type首部中获取编码集，如果没有找到，默认返回 ISO-8859-1)
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(parseNetworkResponseDelegate(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 将服务器返回的内容用gson进行封装
     */
    private T parseNetworkResponseDelegate(String jsonString) {
        return new Gson().fromJson(jsonString, type);
    }


    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
