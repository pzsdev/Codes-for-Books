package com.zhisheng.books.design_patterns.chapter15commandpattern;

/**
 * @ClassName CodeGroup
 * @Description 编码组
 * @Author pengzhisheng
 * @Date 2020/7/15 00:43
 * @Version 1.0
 */
public class CodeGroup extends Group {
    @Override
    public void find() {
        System.out.println("找到编码组。。。");
    }

    @Override
    public void add() {
        System.out.println("客户要求增加一项功能。。。");
    }

    @Override
    public void delete() {
        System.out.println("客户要求删除一项功能。。。");
    }

    @Override
    public void change() {
        System.out.println("客户要求修改一项功能。。。");
    }

    @Override
    public void plan() {
        System.out.println("客户要求功能变更计划。。。");
    }
}
