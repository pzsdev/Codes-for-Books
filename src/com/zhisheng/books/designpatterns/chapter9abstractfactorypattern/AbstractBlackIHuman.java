package com.zhisheng.books.designpatterns.chapter9abstractfactorypattern;

/**
 * 黑色人种
 */
public abstract class AbstractBlackIHuman implements IHuman {
    @Override
    public void getColor() {
        System.out.println("黑色人种的皮肤颜色是黑色的！");
    }

    @Override
    public void talk() {
        System.out.println("黑人会说话，擅长 rap。");
    }
}
