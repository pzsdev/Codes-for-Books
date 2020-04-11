package com.zhisheng.designpatterns.chapter26.version1;

/**
 * 状态模式
 */
public class Client {
    public static void main(String[] args){
        ILift lift = new Lift();

        lift.open();

        lift.close();

        lift.run();

        lift.stop();
    }
}
