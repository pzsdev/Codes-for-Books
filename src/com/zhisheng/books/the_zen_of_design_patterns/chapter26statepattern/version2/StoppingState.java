package com.zhisheng.books.the_zen_of_design_patterns.chapter26statepattern.version2;

public class StoppingState extends LiftState {
    @Override
    public void open(){
        super.context.setLiftState(Context.openningState);
        super.context.getLiftState().open();
    }

    @Override
    public void close(){
        // do nothing
    }

    @Override
    public void run(){
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop(){
        System.out.println("电梯停止了。。。");
    }
}
