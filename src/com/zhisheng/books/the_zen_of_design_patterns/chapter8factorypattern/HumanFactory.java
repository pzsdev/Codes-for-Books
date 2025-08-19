package com.zhisheng.books.the_zen_of_design_patterns.chapter8factorypattern;

/**
 * 人类创建工厂
 */
public class HumanFactory extends AbstractHumanFactory {
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            human = (T)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误！");
        }
        return (T)human;
    }
}
