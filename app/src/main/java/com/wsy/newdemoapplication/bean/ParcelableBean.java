package com.wsy.newdemoapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WSY on 2018/10/18.
 */
public class ParcelableBean implements Parcelable {
    private String name;

    private Integer age;

    //主要是createFromParcel和writeToParcel方法，里面的书写顺序要和上面字段的定义顺序保持一致的；

    public static final Creator<ParcelableBean> CREATOR = new Creator<ParcelableBean>() {
        @Override
        public ParcelableBean createFromParcel(Parcel in) {
            ParcelableBean parcebleBean = new ParcelableBean();
            parcebleBean.setName(in.readString());
            parcebleBean.setAge(in.readInt());

            return parcebleBean;
        }

        @Override
        public ParcelableBean[] newArray(int size) {
            return new ParcelableBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
