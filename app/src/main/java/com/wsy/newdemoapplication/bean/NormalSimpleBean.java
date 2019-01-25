package com.wsy.newdemoapplication.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;

import com.wsy.newdemoapplication.BR;

/**
 * Created by WangSiYe on 2019/1/23.
 */
public class NormalSimpleBean extends BaseObservable {

    private String name;
    private String address;

    public ObservableBoolean isFired = new ObservableBoolean();

    public ObservableArrayMap<String,String> user = new ObservableArrayMap<>();


    public NormalSimpleBean() {
    }

    public NormalSimpleBean(String name, String address) {
        this.name = name;
        this.address = address;
        isFired.set(false);
        user.put("hello","world");
        user.put("hi","world");
        user.put("yo","world");
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }


    public void setIsFired(boolean isFired) {
        this.isFired.set(isFired);
    }
}
