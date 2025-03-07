package com.zhisheng.books.design_patterns.chapter10templatemethodpattern;

public class HummerH2Model extends HummerModel{
    public void start() {
        System.out.println("悍马H2 发动");
    }

    public void enginBoom() {
        System.out.println("悍马H2 引擎声音是这样的。。。");
    }

    public void alarm() {
        System.out.println("悍马H2 鸣笛");
    }

    public void stop() {
        System.out.println("悍马H2 停车。。。");
    }

    public void run() {
        super.run();
    }
}
