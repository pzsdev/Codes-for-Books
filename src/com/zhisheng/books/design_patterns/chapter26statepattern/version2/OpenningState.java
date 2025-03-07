package com.zhisheng.books.design_patterns.chapter26statepattern.version2;

public class OpenningState extends LiftState {
    @Override
    public void open(){
        System.out.println("电梯门开启。。。");
    }

    @Override
    public void close(){
        super.context.setLiftState(Context.closingState);
        super.context.getLiftState().close();
    }

    @Override
    public void run(){
        // do nothing
    }

    @Override
    public void stop(){
        // do nothing
    }
}
