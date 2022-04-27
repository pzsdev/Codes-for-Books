package com.zhisheng.books.designpatterns.chapter8factorypattern;

/**
 * 工厂模式
 *
 * 场景类，女娲
 */
public class NvWa {
    public static void main(String[] args) {
        // 声明阴阳八卦炉
        AbstractHumanFactory yinYangLu = new HumanFactory();

        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHunam = yinYangLu.createHuman(WhiteHuman.class);
        whiteHunam.getColor();
        whiteHunam.talk();

        System.out.println("--造出的第二批是黑色人种--");
        Human blackHuam = yinYangLu.createHuman(BlackHuman.class);
        blackHuam.getColor();
        blackHuam.talk();

        System.out.println("--造出的第三批是黄色人种--");
        Human yellowHuman = yinYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
