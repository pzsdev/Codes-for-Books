package com.zhisheng.books.design_patterns.chapter15commandpattern;

/**
 * @ClassName PageGroup
 * @Description 美工组
 * @Author pengzhisheng
 * @Date 2020/7/15 00:41
 * @Version 1.0
 */
public class PageGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到美工组。。。");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加一个页面。。。");
    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一个页面。。。");
    }

    @Override
    public void change() {
        System.out.println("客户要求修改一个页面。。。");
    }

    @Override
    public void plan() {
        System.out.println("客户要求页面变更计划。。。");
    }
}
