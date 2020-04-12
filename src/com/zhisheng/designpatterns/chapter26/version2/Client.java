package com.zhisheng.designpatterns.chapter26.version2;

public class Client {
    public static void main(String[] args){
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
