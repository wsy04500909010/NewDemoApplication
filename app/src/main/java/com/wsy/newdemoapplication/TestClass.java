package com.wsy.newdemoapplication;

import java.util.Random;

/**
 * Created by WSY on 2018/9/22.
 */

public class TestClass {
    public static void main(String[] args) {
//        int number = 10;        //原始数二进制
//        printInfo(number);
//        number = number << 1;
//        //左移一位
//        printInfo(number);
//        number = number >> 1;
//        //右移一位
//        printInfo(number);

        Random rd = new Random(47);
        int i = rd.nextInt();
        printInfo(i);
        printInfo(i<<5);
        printInfo(i>>5);
        printInfo(i>>>5);

    }

    /**
     * 输出一个int的二进制数
     *
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
