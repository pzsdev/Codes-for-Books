package com.zhisheng.books.design_patterns.chapter15commandpattern;

/**
 * @ClassName RequirementGroup
 * @Description 需求组
 * @Author pengzhisheng
 * @Date 2020/7/15 00:38
 * @Version 1.0
 */
public class RequirementGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到需求组。。。");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加意向需求。。。");
    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一项需求。。。");
    }

    @Override
    public void change() {
        System.out.println("客户要求修改一项需求。。。");
    }

    @Override
    public void plan() {
        System.out.println("客户要求需求变更计划。。。");
    }
}
