package com.wsy.newdemoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wsy.newdemoapplication.bean.Translation;
import com.wsy.newdemoapplication.bean.TranslationPOST;
import com.wsy.newdemoapplication.interf.TaoBaoHttps_Interface;
import com.wsy.newdemoapplication.interf.Translation_Interface;
import com.wsy.newdemoapplication.interf.Translation_post_Interface;
import com.wsy.newdemoapplication.tools.OkHttp3Utils;
import com.wsy.newdemoapplication.tools.SslContextFactory;
import com.wsy.newdemoapplication.tools.TrustAllCerts;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WSY on 2018/6/10.
 */

public class RetrofitDemoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Button button_ori = (Button) findViewById(R.id.btn_translation_ori);
        Button btn_translation = (Button) findViewById(R.id.btn_translation);
        Button btn_https_taobao = (Button) findViewById(R.id.btn_https_taobao);
        Button btn_http_post = (Button) findViewById(R.id.btn_http_post);

        button_ori.setOnClickListener(this);
        btn_translation.setOnClickListener(this);
        btn_https_taobao.setOnClickListener(this);
        btn_http_post.setOnClickListener(this);


    }

    //GET测试用的两个网址
    //https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb
    //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world

    //POST测试用的网址
    // 参数说明
    // doctype：json 或 xml
    // jsonversion：如果 doctype 值是 xml，则去除该值，若 doctype 值是 json，该值为空即可
    // xmlVersion：如果 doctype 值是 json，则去除该值，若 doctype 值是 xml，该值为空即可
    // type：语言自动检测时为 null，为 null 时可为空。英译中为 EN2ZH_CN，中译英为 ZH_CN2EN，日译中为 JA2ZH_CN，中译日为 ZH_CN2JA，韩译中为 KR2ZH_CN，中译韩为 ZH_CN2KR，中译法为 ZH_CN2FR，法译中为 FR2ZH_CN
    // keyform：mdict. + 版本号 + .手机平台。可为空
    // model：手机型号。可为空
    // mid：平台版本。可为空
    // imei：???。可为空
    // vendor：应用下载平台。可为空
    // screen：屏幕宽高。可为空
    // ssid：用户名。可为空
    // abtest：???。可为空

    // 请求方式说明
    // 请求方式：POST
    // 请求体：i
    // 请求格式：x-www-form-urlencoded
    //http://fanyi.youdao.com/translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_translation_ori:
                Retrofit retrofit1 = new Retrofit.Builder().baseUrl("http://fy.iciba.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Translation_Interface request1 = retrofit1.create(Translation_Interface.class);
                Call<ResponseBody> call1 = request1.getTranslation_Ori();
                call1.enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        try {
                            System.out.println(new String(response.body().bytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


                break;
            case R.id.btn_translation:

                OkHttpClient okHttpClient1 = OkHttp3Utils.getOkHttpSingletonInstance();

                Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://fy.iciba.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient1)
                        .build();

                Translation_Interface request2 = retrofit2.create(Translation_Interface.class);

                Call<Translation> call2 = request2.getTranslation();
                call2.enqueue(new Callback<Translation>() {
                    @Override
                    public void onResponse(Call<Translation> call, Response<Translation> response) {
                        response.body().show();
                    }

                    @Override
                    public void onFailure(Call<Translation> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_https_taobao:
//                int[] ints = {R.raw.taobaobks};
//                String[] hostUrls = {"https://sugges.taobao.com/"};
//                OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
//                        .sslSocketFactory(SslContextFactory.getSSLSocketFactory(this, ints))
//                        .hostnameVerifier(new HostnameVerifier() {
//                            @Override
//                            public boolean verify(String hostname, SSLSession session) {
//                                return true;
//                            }
//                        });



                OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
                mBuilder.sslSocketFactory(createSSLSocketFactory());
                mBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
                OkHttpClient client = mBuilder.build();


                SSLSocketFactory sslSocketFactory = new SslContextFactory().getSslSocket(this).getSocketFactory();
                OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory);

                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl("https://sugges.taobao.com/")
                        .client(client)
//                        .client(okHttpClient.build())
//                        .client(okHttpClient.build())
                        .build();

                TaoBaoHttps_Interface request3 = retrofit3.create(TaoBaoHttps_Interface.class);

                Call<ResponseBody> call3 = request3.getCall();
                call3.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(new String(response.toString()));
//                            System.out.println(new String(response.body().bytes()));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_http_post:
                Retrofit retrofit4 = new Retrofit.Builder()
                        .baseUrl("http://fanyi.youdao.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Translation_post_Interface request4 = retrofit4.create(Translation_post_Interface.class);

                Call<TranslationPOST> call4 = request4.getCall("I love you");

                call4.enqueue(new Callback<TranslationPOST>() {
                    @Override
                    public void onResponse(Call<TranslationPOST> call, Response<TranslationPOST> response) {
                        System.out.println(response.body().getTranslateResult().get(0).get(0).getTgt());
                    }

                    @Override
                    public void onFailure(Call<TranslationPOST> call, Throwable t) {

                    }
                });


                break;
        }
    }

    private static class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }


    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

}
