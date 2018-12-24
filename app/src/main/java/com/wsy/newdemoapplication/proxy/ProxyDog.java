package com.wsy.newdemoapplication.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by WSY on 2018/11/2.
 */
public class ProxyDog {

    private Object target;

    public ProxyDog(Object target) {
        this.target = target;
    }


    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("call")) {
                            Log.e("proxydog", "haha");
                            return null;
                        } else {
                            return method.invoke(target, args);
                        }
                    }
                });
    }
}
