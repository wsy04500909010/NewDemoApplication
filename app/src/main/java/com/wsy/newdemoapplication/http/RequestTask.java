package com.wsy.newdemoapplication.http;

import android.util.Log;

import com.wsy.newdemoapplication.http.urlconnection.URLConnectionFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import retrofit2.http.Url;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class RequestTask implements Runnable {

    private Request request;
    private HttpListener httpListener;


    public RequestTask(Request request, HttpListener httpListener) {
        this.request = request;
        this.httpListener = httpListener;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("request", request.toString());

        Exception e = null;
        int responseCode = -1;
        Map<String, List<String>> responseHeaders = null;
        HttpURLConnection httpURLConnection = null;

        String urlstr = request.getUrl();
        RequestMethod method = request.getMethod();

        try {
            URL url = new URL(urlstr);

            //一句话切换 okhttp 和 URLConnection
//            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection = URLConnectionFactory.getInstance().openUrl(url);

            if (httpURLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;

                SSLSocketFactory sslSocketFactory = request.getSslSocketFactory();
                if (sslSocketFactory != null) {

                    httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
                }
                HostnameVerifier hostnameVerifier = request.getHostnameVerifier();
                if (hostnameVerifier != null) {

                    httpsURLConnection.setHostnameVerifier(hostnameVerifier);
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (httpURLConnection != null) {

                httpURLConnection.disconnect();
            }
        }


        Response response = null;
        Message message = new Message(response, httpListener);

        PosterHandler.getInstance().post(message);

    }
}
