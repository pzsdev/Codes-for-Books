package com.zhisheng.designpatterns.chapter10templatemethodpattern;

/**
 * 模版方法模式 Template Method Pattern
 *
 * 场景类
 */
public class Client {
    public static void main(String[] args) {
        HummerModel h1 = new HummerH1Model();
        h1.run();
    }
}
