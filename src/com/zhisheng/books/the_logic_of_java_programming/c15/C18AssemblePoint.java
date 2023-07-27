package com.zhisheng.books.the_logic_of_java_programming.c15;

/**
 * @ClassName C18AssemblePoint
 * @Description 集合点，协作对象
 * @Author pengzhisheng
 * @Date 2022/5/3 04:41
 * @Version 1.0
 **/
public class C18AssemblePoint {
    private int n;
    public C18AssemblePoint(int n) {
        this.n = n;
    }

    public synchronized void await() throws InterruptedException {
        if (n > 0) {
            n--;
            if (n == 0) {
                System.out.println("all arrived");
                notifyAll();
            } else {
                System.out.println("not all arrived");
                while (n != 0) {
                    wait();
                }
            }
        }
    }
}
