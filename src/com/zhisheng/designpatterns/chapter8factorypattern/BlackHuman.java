package com.zhisheng.designpatterns.chapter8factorypattern;

/**
 * 黑人
 */
public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑色人种的皮肤是黑色的！");
    }

    @Override
    public void talk() {
        System.out.println("黑人会说话，擅长 rap。");
    }
}
