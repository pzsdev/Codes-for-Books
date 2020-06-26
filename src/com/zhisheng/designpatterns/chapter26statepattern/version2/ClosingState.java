package com.zhisheng.designpatterns.chapter26statepattern.version2;

public class ClosingState extends LiftState {
    @Override
    public void open(){
        super.context.setLiftState(Context.openningState);
        super.context.getLiftState().open();
    }

    @Override
    public void close(){
        System.out.println("电梯门关闭了。。。");
    }

    @Override
    public void run(){
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop(){
        super.context.setLiftState(Context.stoppingState);
        super.context.getLiftState().stop();
    }
}
