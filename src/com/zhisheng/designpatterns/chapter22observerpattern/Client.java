package com.zhisheng.designpatterns.chapter22observerpattern;

/**
 * 观察者模式（Observer Pattern）
 *
 * 场景类
 */
public class Client {
    public static void main(String[] args) {
        // 三个观察者
        Observer liSi = new LiSi();
        Observer wangSi = new WangSi();
        Observer liuSi = new LiuSi();
        // 定义韩非子
        HanFeiZi hanFeiZi = new HanFeiZi();
        // 添加观察者
        hanFeiZi.addObserver(liSi);
        hanFeiZi.addObserver(wangSi);
        hanFeiZi.addObserver(liuSi);
        // 韩非子开始活动
        hanFeiZi.haveBreakfast();

        hanFeiZi.haveFun();
    }
}
