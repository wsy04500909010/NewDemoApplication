package com.wsy.newdemoapplication.http;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public enum RequestMethod {

    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE");


    private String value;

    RequestMethod(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
