package com.wsy.newdemoapplication.interf;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by WSY on 2018/6/10.
 */

public interface TaoBaoHttps_Interface {

    @GET("sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb")
    Call<ResponseBody> getCall();
    // @GET注解的作用:采用Get方法发送网络请求

    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
}
