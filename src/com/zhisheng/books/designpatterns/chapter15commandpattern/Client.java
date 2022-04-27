package com.zhisheng.books.designpatterns.chapter15commandpattern;

/**
 * @ClassName Client
 * @Description 命令模式（command pattern）
 *
 * Encapsulate a request as an object, thereby letting ypu parameterize clients
 * with different requests, queue or log requests, and support undoable operations.
 * 将请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或记录请求日志，
 * 可以提供命令的撤销和恢复功能。
 *
 * 场景类
 *
 * 甲方、乙方干活对接（甲方只和乙方负责人对接，具体的活由乙方负责人向下传递）
 *
 * @Author pengzhisheng
 * @Date 2020/7/15 00:15
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        // 定义乙方负责人
        Invoker invoker = new Invoker();
        System.out.println("----------甲方客户要求增加一项需求----------");
        Command command = new AddRequirementCommand();
        // 负责人接收到甲方的需求
        invoker.setCommand(command);
        // 乙方负责人开始让手下的人干活
        invoker.action();
        System.out.println("----------过了几天，甲方又有新需求了----------");
        System.out.println("----------甲方客户要求删除一个页面----------");
        Command command1 = new DeletePagecCommand();
        invoker.setCommand(command1);
        invoker.action();
    }
}
