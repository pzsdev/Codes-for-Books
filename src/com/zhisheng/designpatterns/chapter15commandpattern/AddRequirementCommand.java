package com.zhisheng.designpatterns.chapter15commandpattern;

/**
 * @ClassName AddRequirementCommand
 * @Description 增加需求的命令
 * @Author pengzhisheng
 * @Date 2020/7/15 00:46
 * @Version 1.0
 */
public class AddRequirementCommand extends Command {
    // 执行增加一项需求的命令
    @Override
    public void execute() {
        // 找到需求组
        super.requirementGroup.find();
        // 需求组增加一项需求
        super.requirementGroup.add();
        // 给出计划
        super.requirementGroup.plan();
    }
}
