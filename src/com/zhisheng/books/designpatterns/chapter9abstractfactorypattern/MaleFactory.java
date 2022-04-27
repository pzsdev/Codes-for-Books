package com.zhisheng.books.designpatterns.chapter9abstractfactorypattern;

/**
 * 生产男性的八卦炉
 */
public class MaleFactory implements IHumanFactory {
    @Override
    public IHuman createYellowHuman() {
        return new MaleYellowIHuman();
    }

    @Override
    public IHuman createWhiteHuman() {
        return new MaleWhiteIHuman();
    }

    @Override
    public IHuman createBlackHuman() {
        return new MaleBlackIHuman();
    }
}
