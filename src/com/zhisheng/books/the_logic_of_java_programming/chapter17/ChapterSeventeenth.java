package com.zhisheng.books.the_logic_of_java_programming.chapter17;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/25
 **/
public class ChapterSeventeenth {
    public static void main(String[] args) {
        ChapterSeventeenth chapterSeventeenth = new ChapterSeventeenth();
        chapterSeventeenth.copyOnWriteArrayListDemo();
    }

    private void copyOnWriteArrayListDemo() {
        List<String> list = new CopyOnWriteArrayList<>();
        new WriteThread(list).start();
        new ReadThread(list).start();
        new ReadThread(list).start();
    }

    static class WriteThread extends Thread {
        private List<String> list;

        public WriteThread(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                list.add("a" + i);
            }
        }
    }

    static class ReadThread extends Thread {
        private List<String> list;

        public ReadThread(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                String s = list.get(i);
                System.out.println(Thread.currentThread().getName() + " " + s);
            }
        }
    }
}
