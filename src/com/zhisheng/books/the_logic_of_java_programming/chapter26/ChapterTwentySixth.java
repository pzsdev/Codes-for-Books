package com.zhisheng.books.the_logic_of_java_programming.chapter26;

import java.time.Instant;
import java.util.Date;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/11/29
 **/
public class ChapterTwentySixth {

    public static void main(String[] args) {
        instantDemo();
    }

    public static void print(String s) {
        System.out.println(s);
    }

    private static void instantDemo() {
        Instant now = Instant.now();
        print(now.toEpochMilli() + "");
        print(now.toString());

        print(System.currentTimeMillis() + "");
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        print(instant.toString());

        Date date = new Date();
        print(date.toString());
    }
}
