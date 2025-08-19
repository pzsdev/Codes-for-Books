package com.zhisheng.books.the_zen_of_design_patterns.chapter16chainofresponsibilitypattern;

/**
 * @ClassName Handler
 * @Description 责任链上的抽象处理类
 * @Author pengzhisheng
 * @Date 2020/7/18 03:13
 * @Version 1.0
 */
public abstract class Handler {
    public final static int FATHER_LEVEL_REQUEST = 1;
    public final static int HUSBAND_LEVEL_REQUEST = 2;
    public final static int SON_LEVEL_REQUEST = 3;
    // 能处理的级别
    private int level = 0;
    // 责任传递，下一个处理（负责）人是谁
    private Handler nextHandler;
    // 每个类都要说明一下自己能处理哪些请求
    public Handler(int level) {
        this.level = level;
    }
    // 一个女性（女儿、妻子、母亲）要求逛街，你要根据她当时的状态安排不同的人来处理请求
    public final void HandlerMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.HandlerMessage(women);
            } else {
                System.out.println("---没地方请示了，按不同意处理---\n");
            }
        }
    }
    /**
     * 如果不属于你处理的请求，你应该帮她找下一个处理人，如女儿出嫁了（请求的状态），
     * 首先还是向父亲（责任链第一责任人）请示是否可以逛街（请求，是否可以做某件事），
     * 那父亲就应该告诉女儿，应该去找她丈夫（下一个责任人）请求
     */
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }
    // 如果属于你的责任，那就要对请求做出相应处理
    // （此处抽象方法，具体的处理由每个具体类做具体实现）
    protected abstract void response(IWomen women);
}
