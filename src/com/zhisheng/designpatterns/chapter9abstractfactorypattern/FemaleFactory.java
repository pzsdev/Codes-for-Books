package com.zhisheng.designpatterns.chapter9abstractfactorypattern;

import com.zhisheng.designpatterns.chapter8factorypattern.Human;

/**
 * 生产女性的八卦炉
 */
public class FemaleFactory implements IHumanFactory {
    @Override
    public IHuman createYellowHuman() {
        return new FemaleYellowIHuman();
    }

    @Override
    public IHuman createWhiteHuman() {
        return new FemaleWhiteIHuman();
    }

    @Override
    public IHuman createBlackHuman() {
        return new FemaleBlackIHuman();
    }
}
