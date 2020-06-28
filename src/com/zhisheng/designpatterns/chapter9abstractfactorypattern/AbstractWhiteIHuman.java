package com.zhisheng.designpatterns.chapter9abstractfactorypattern;

/**
 * 白色人种
 */
public abstract class AbstractWhiteIHuman implements IHuman {
    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白色的！");
    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话，一般说的都是单音节。");
    }
}
