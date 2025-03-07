package com.zhisheng.books.design_patterns.chapter15commandpattern;

/**
 * @ClassName Invoker
 * @Description 乙方负责人
 * @Author pengzhisheng
 * @Date 2020/7/15 00:50
 * @Version 1.0
 */
public class Invoker {
    // 什么命令
    private Command command;
    // 客户发出命令
    public void setCommand(Command command) {
        this.command = command;
    }
    // 接到了客户端命令，现在执行客户的命令
    public void action() {
        this.command.execute();
    }
}
