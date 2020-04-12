package com.zhisheng.designpatterns.chapter26.version2;

public class Context {
    public final static OpenningState openningState = new OpenningState();
    public final static ClosingState closingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();

    private LiftState liftState;

    public void setLiftState(LiftState liftState){
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public LiftState getLiftState(){
        return liftState;
    }

    public void open(){
        this.liftState.open();
    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }
}
