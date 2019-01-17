package com.wsy.newdemoapplication.bean;

import java.io.File;

/**
 * Created by WangSiYe on 2019/1/14.
 */
public class KeyValue {

    private String key;
    private Object value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public KeyValue(String key, File value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
