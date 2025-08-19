package com.zhisheng.books.the_zen_of_design_patterns.chapter26statepattern.version1;

/**
 * 状态模式
 */
public class Client {
    public static void main(String[] args){
        ILift lift = new Lift();

        // 电梯的初始条件应该是停止状态
        lift.setState(ILift.STOPPING_STATE);

        // 首先是电梯门开启，人进去
        lift.open();

        // 然后电梯门关闭
        lift.close();

        // 之后，电梯运行起来，向上或者向下
        lift.run();

        // 最后，到达目标楼层，电梯停止运行
        lift.stop();
    }
}
