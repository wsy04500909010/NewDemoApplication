package com.wsy.newdemoapplication.http;

import com.wsy.newdemoapplication.bean.KeyValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class Request {

    private String url;
    private RequestMethod method;
    private List<KeyValue> keyValues;

    private SSLSocketFactory sslSocketFactory;
    private HostnameVerifier hostnameVerifier;

    public Request(String url, RequestMethod method, List<KeyValue> keyValues) {
        this.url = url;
        this.method = method;
        this.keyValues = keyValues;
    }

    public Request(String url) {
        this(url, RequestMethod.GET);

    }

    public Request(String url, RequestMethod method) {

        this.url = url;
        this.method = method;

        keyValues = new ArrayList<>();
    }

    /**
     * 设置ssl证书
     * @param sslSocketFactory {@link SSLSocketFactory}
     */
    public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    /**
     * 设置服务器主机认证规则
     * @param hostnameVerifier {@link HostnameVerifier}
     */
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }


    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public String getUrl() {
        return url;
    }


    public RequestMethod getMethod() {
        return method;
    }


    public void add(String key, String value) {
        keyValues.add(new KeyValue(key, value));

    }

    public void add(String key, File value) {
        keyValues.add(new KeyValue(key, value));
    }

    @Override
    public String toString() {

        return "url=" + url + "\n" + "method=" + method + "\n" + "params=" + keyValues.toString();
    }
}
