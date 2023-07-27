package com.zhisheng.books.the_logic_of_java_programming.chapter7;

import java.util.Date;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/7/27
 **/
public class ChapterSeven {
    public static void main(String[] args) {
        System.out.println("Chapter Seven");

        ChapterSeven chapterSeven = new ChapterSeven();
        chapterSeven.date();
        chapterSeven.timeZone();
    }

    /**
     * 7.5 剖析日期与时间
     *
     * @apiNote
     * 7.5.1 基本概念
     * 时区、时刻、纪元时、年历
     *
     * 7.5.2 日期和时间 API
     * - Date：时刻
     * - Calendar：年历，抽象类，表示公历的子类 GregorianCalendar
     * - DateFormat：格式化日期和时间，将日期和时间转换为字符串，或者将字符串转换为日期和时间，抽象类，最常用的子类是 SimpleDateFormat
     * - TimeZone：时区
     * - Locale：国家（或地区）和语言
     *
     *
     */
    public void date() {
        /* Date */
        // Date 两个构造方法
        // 无参构造方法，构造一个表示当前时间的 Date 对象
        Date date = new Date();
        System.out.println(date);

        // 根据传入的毫秒数构造一个 Date 对象
        Date date1 = new Date(1000);
        System.out.println(date1);

        // Date 常用方法
        long time = date.getTime();//返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数
        System.out.println(time);

        boolean equals = date.equals(date1);//比较两个日期是否相等
        System.out.println(equals);

        int compare = date.compareTo(date1);//比较两个日期的先后顺序
        System.out.println(compare);

        boolean before = date.before(date1);//判断当前日期是否在指定日期之前
        System.out.println(before);

        boolean after = date.after(date1);//判断当前日期是否在指定日期之后
        System.out.println(after);

        int hashCode = date.hashCode();//返回此对象的哈希码值
        System.out.println(hashCode);
    }

    public void timeZone() {
        /* TimeZone */
        TimeZone timeZone = TimeZone.getDefault();//获取当前时区
        System.out.println(timeZone);
    }
}
