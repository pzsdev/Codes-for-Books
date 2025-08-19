package com.zhisheng.books.the_zen_of_design_patterns.chapter15commandpattern;

/**
 * @ClassName Command
 * @Description 抽象命令类
 * @Author pengzhisheng
 * @Date 2020/7/15 00:44
 * @Version 1.0
 */
public abstract class Command {
    protected RequirementGroup requirementGroup = new RequirementGroup();

    protected PageGroup pageGroup = new PageGroup();

    protected CodeGroup codeGroup = new CodeGroup();

    // 只有一个方法，你要我做什么事情
    public abstract void execute();
}
