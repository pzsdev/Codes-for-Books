package com.zhisheng.books.designpatterns.chapter9abstractfactorypattern;

/**
 * 八卦炉定义
 */
public interface IHumanFactory {
    IHuman createYellowHuman();

    IHuman createWhiteHuman();

    IHuman createBlackHuman();
}
