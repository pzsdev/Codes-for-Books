package com.zhisheng.books.the_zen_of_design_patterns.chapter9abstractfactorypattern;

/**
 * 场景类，女娲
 */
public class NvWa {
    public static void main(String[] args) {
        IHumanFactory maleHumanFactory = new MaleFactory();

        IHumanFactory femaleHumanFactory = new FemaleFactory();

        IHuman maleYellowHuman = maleHumanFactory.createYellowHuman();

        IHuman femaleYellowHuman = femaleHumanFactory.createYellowHuman();

        System.out.println("---生产一个女性黄种人");
        femaleYellowHuman.getColor();
        femaleYellowHuman.talk();
        femaleYellowHuman.getSex();

        System.out.println("\n---成产一个男性黄种人");
        maleYellowHuman.getColor();
        maleYellowHuman.talk();
        maleYellowHuman.getSex();
    }

}
