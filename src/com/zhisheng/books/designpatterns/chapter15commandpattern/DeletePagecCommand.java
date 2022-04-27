package com.zhisheng.books.designpatterns.chapter15commandpattern;

/**
 * @ClassName DeletePagecCommand
 * @Description 删除页面的命令
 * @Author pengzhisheng
 * @Date 2020/7/15 00:48
 * @Version 1.0
 */
public class DeletePagecCommand extends Command {
    @Override
    public void execute() {
        super.pageGroup.find();
        super.pageGroup.delete();
        super.pageGroup.plan();
    }
}
