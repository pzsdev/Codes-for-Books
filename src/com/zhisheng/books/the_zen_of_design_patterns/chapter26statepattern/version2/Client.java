package com.zhisheng.books.the_zen_of_design_patterns.chapter26statepattern.version2;

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
