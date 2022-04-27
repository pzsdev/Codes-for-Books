package com.zhisheng.books.designpatterns.chapter8factorypattern;

/**
 * 黄种人
 */
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄种人的皮肤颜色是黄色的！");
    }

    @Override
    public void talk() {
        System.out.println("黄种人会说话，一般都是是双字节。");
    }
}
