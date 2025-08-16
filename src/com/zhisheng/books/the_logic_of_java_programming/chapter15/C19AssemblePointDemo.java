package com.zhisheng.books.the_logic_of_java_programming.chapter15;

/**
 * @ClassName C19AssemblePointDemo
 * @Description 集合点
 * 各个线程先是分头行动，各自到达一个集合点，在集合点需要集齐所有线程，交换数据，然后再进行下一步动作
 * @Author pengzhisheng
 * @Date 2022/5/3 04:43
 * @Version 1.0
 **/
public class C19AssemblePointDemo {
    static class Tourist extends Thread {
        C18AssemblePoint ap;
        public Tourist(C18AssemblePoint ap) {
            this.ap = ap;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立行动
                System.out.println("tourist walk around ");
                Thread.sleep((int) (Math.random() * 1000));
                // 集合
                ap.await();
                System.out.println("arrived");
                // 集合后执行其他操作

            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        int num = 10;
        Tourist[] tourists = new Tourist[num];
        C18AssemblePoint ap = new C18AssemblePoint(num);
        for (int i = 0; i < num; i++) {
            tourists[i] = new Tourist(ap);
            tourists[i].start();
        }
    }
}
