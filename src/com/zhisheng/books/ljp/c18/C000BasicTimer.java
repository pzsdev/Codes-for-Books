package com.zhisheng.books.ljp.c18;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName C000BasicTimer
 * @Description 创建一个 Timer 对象，1秒钟后运行 DelayTask，最后调用 Timer 的 cancel 方法取消所有定时任务
 * @Author pengzhisheng
 * @Date 2022/5/26 23:46
 * @Version 1.0
 **/
public class C000BasicTimer {
    static class DelayTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("delay task");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        timer.schedule(new DelayTask(), 1000);
        Thread.sleep(2000);
        timer.cancel();
    }
}
