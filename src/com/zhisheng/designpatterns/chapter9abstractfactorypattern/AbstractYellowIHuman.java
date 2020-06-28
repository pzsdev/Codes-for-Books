package com.zhisheng.designpatterns.chapter9abstractfactorypattern;

/**
 * 黄种人
 */
public abstract class AbstractYellowIHuman implements IHuman {
    @Override
    public void getColor() {
        System.out.println("黄种人的皮肤颜色是黄色的！");
    }

    @Override
    public void talk() {
        System.out.println("黄种人会说话，一般说的都是双音节。");
    }
}
