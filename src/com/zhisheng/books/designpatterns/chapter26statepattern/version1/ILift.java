package com.zhisheng.books.designpatterns.chapter26statepattern.version1;

/**
 * 电梯接口
 * <p>
 * 包含四个状态
 */
public interface ILift {

    /**
     * The constant OPENING_STATE.
     */
    int OPENING_STATE = 1;
    /**
     * The constant CLOSING_STATE.
     */
    int CLOSING_STATE = 2;
    /**
     * The constant RUNNING_STATE.
     */
    int RUNNING_STATE = 3;
    /**
     * The constant STOPPING_STATE.
     */
    int STOPPING_STATE = 4;

    /**
     * 设置电梯的状态
     *
     * @param state the state
     */
    void setState(int state);

    /**
     * Open.
     */
    void open();

    /**
     * Close.
     */
    void close();

    /**
     * Run.
     */
    void run();

    /**
     * Stop.
     */
    void stop();
}
