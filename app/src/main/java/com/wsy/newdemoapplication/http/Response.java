package com.wsy.newdemoapplication.http;

import java.util.List;
import java.util.Map;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class Response {

    private String responseCode;
    private String result;
    private Exception exception;

    private Request request;
    private Map<String,List<String>> responseHead;//服务器响应头


    Response(String responseCode, String result, Exception exception, Request request,
            Map<String,List<String>> responseHead) {
        this.responseCode = responseCode;
        this.result = result;
        this.exception = exception;
        this.request = request;
        this.responseHead = responseHead;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResult() {
        return result;
    }

    public Request getRequest() {
        return request;
    }

    public Exception getException() {
        return exception;
    }


    public Map<String, List<String>> getResponseHead() {
        return responseHead;
    }

}
