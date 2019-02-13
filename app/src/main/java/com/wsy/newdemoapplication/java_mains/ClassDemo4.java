package com.wsy.newdemoapplication.java_mains;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by WangSiYe on 2019/1/25.
 */
public class ClassDemo4 {

    public static void main(String[] args) {
//        获取print(int,int)方法

        try {
            //两种写法
//            Method method = A.class.getMethod("print",new Class[]{int.class,int.class});
            Method method = A.class.getMethod("print", int.class, int.class);
            //方法的反射操作 通过method对象进行操作
            A a1 = new A();

            //方法没有返回值  返回null 不然返回真实数据 不过是Object类型需要强转
//            Object o = method.invoke(a1, new Object[]{10, 20});
            Object o = method.invoke(a1, 10, 20);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


}

class A {
    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a + "+" + b);
    }
}
