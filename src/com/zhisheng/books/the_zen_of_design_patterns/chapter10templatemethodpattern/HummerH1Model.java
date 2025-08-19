package com.zhisheng.books.the_zen_of_design_patterns.chapter10templatemethodpattern;

/**
 * H1 型号悍马模型
 */
public class HummerH1Model extends HummerModel {
    public void start() {
        System.out.println("悍马H1 发动");
    }

    public void enginBoom() {
        System.out.println("悍马H1 引擎声音是这样的。。。");
    }

    public void alarm() {
        System.out.println("悍马H1 鸣笛");
    }

    public void stop() {
        System.out.println("悍马H1 停车。。。");
    }

    public void run() {
        super.run();
    }
}
