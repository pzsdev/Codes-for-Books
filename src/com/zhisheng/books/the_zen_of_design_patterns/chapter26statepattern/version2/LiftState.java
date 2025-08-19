package com.zhisheng.books.the_zen_of_design_patterns.chapter26statepattern.version2;

public abstract class LiftState {
    protected Context context;

    public void setContext(Context context){
        this.context = context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();
}
