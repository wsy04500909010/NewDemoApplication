package com.wsy.newdemoapplication.java_mains;

import com.wsy.newdemoapplication.bean.MyDog;

/**
 * Created by WangSiYe on 2019/1/21.
 */
public class ClassDemo {

    public static void main(String[] args) {
        //MyDog 实例
        MyDog myDog = new MyDog();

        //MyDog 这个类 也是一个实例对象 Class类的实例对象 该如何表示呢
        //任何一个类 都是Class的实例对象 这个实例对象有三种表示方式

        //第一种表示方式 -->实际在告诉我们每个类都有一个隐含的静态成员对象
        Class c1 = MyDog.class;
        //第二种表示方式 -->已经知道该类对象 通过getClass方式
        Class c2 = myDog.getClass();

        /*官网 c1 c2 表示了MyDog类的类类型(class type)
           万事万物皆对象
           类也是对象
           这个对象我们成为该类的类类型

         */

        //不管c1 or c2都代表了MyDog的类类型 一个类只可能是Class类的一个实例对象
        System.out.println(c1 == c2);


        //第三种表达方式
        Class c3 = null;
        try {
            c3 = Class.forName("com.wsy.newdemoapplication.bean.MyDog");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c2 == c3);

        //我们可以通过类的类类型 创建该类的实例
        //通过c1 c2 c3 创建出mydog

        try {
            MyDog myDog1 = (MyDog) c1.newInstance();
            myDog1.call();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


}
