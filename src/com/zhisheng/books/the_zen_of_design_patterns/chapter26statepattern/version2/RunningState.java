package com.zhisheng.books.the_zen_of_design_patterns.chapter26statepattern.version2;

public class RunningState extends LiftState {
    @Override
    public void open(){
        // do nothing
    }

    @Override
    public void close(){
        // do nothing
    }

    @Override
    public void run(){
        System.out.println("电梯上下运行。。。");
    }

    @Override
    public void stop(){
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}
