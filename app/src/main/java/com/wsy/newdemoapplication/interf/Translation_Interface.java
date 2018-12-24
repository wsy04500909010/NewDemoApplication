package com.wsy.newdemoapplication.interf;


import com.wsy.newdemoapplication.bean.Translation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by WSY on 2018/6/18.
 */

public interface Translation_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getTranslation();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<ResponseBody> getTranslation_Ori();//获取原始数据的方法
}
