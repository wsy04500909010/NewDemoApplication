package com.wsy.newdemoapplication.java_mains;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by WangSiYe on 2019/1/21.
 */
public class ClassUtil {
    //Class类的基本API

    //打印类的信息 包括类的成员函数 成员变量
    public static void printObjectMessage(Object object) {

        Class c = object.getClass();
        //获取类的名称
        System.out.println("类的名称是" + c.getSimpleName());

        //Method 类  方法对象
        //一个成员方法就是一个Method对象
        //getMethods() 获得的是public声明的方法 包括从父类继承下来的方法
        //getDeclaredMethods()获得的是所有该类自己声明的方法 不论访问权限  不包含从父类继承的

        Method[] ms = c.getMethods();//c.getDeclaredMethods()

        for (int i = 0; i < ms.length; i++) {
            //得到方法的返回值类型的类类型
            Class returnType = ms[i].getReturnType();
            System.out.print(returnType.getName() + " ");
            //得到方法的名称
            System.out.print(ms[i].getName() + "(");
            //获取参数类型 -->得到的是方法参数列表的类型的类类型
            Class[] paramTypes = ms[i].getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName());
            }
            System.out.println(")");

        }

        //打印成员变量信息
        printFieldMessage(c);


    }

    public static void printFieldMessage(Class c) {
    /*
    成员变量也是对象
    java.lang.reflect.Field
    Field类封装了关于成员变量的操作
    getFields()方法获取的是所有的public声明的成员变量信息
    getDeclaredFields获取的是该类自己声明的成员变量信息
     */

//        Field[] fs  = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            //得到成员变量的类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    //打印对象的构造函数的信息
    public static void printConMessage(Object obj) {
        Class c = obj.getClass();

        //构造函数也是对象
        //java.lang.Constructor 中封装了构造函数的信息

//        Constructor constructor = c.getConstructor();
        Constructor[] constructor = c.getDeclaredConstructors();
        for (Constructor constructor1 : constructor) {
            System.out.println(constructor1.getName() + " ");
            //获取构造函数参数列表值的类类型
            Class[] params = constructor1.getParameterTypes();
            for (Class class1 : params) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }

    }


}
