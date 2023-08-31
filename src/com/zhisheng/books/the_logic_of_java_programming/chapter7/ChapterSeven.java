package com.zhisheng.books.the_logic_of_java_programming.chapter7;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
//        chapterSeven.date();
//        chapterSeven.timeZone();
//        chapterSeven.locale();
        chapterSeven.calender();
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
        System.out.println(timeZone.getID());

        String property = System.getProperty("user.timezone");
        System.out.println(property);

        // 获取任意时区实例
        TimeZone timeZone1 = TimeZone.getTimeZone("GMT+8");
        System.out.println(timeZone1.getID());
        TimeZone timeZone2 = TimeZone.getTimeZone("US/Central");
        System.out.println(timeZone2.getID());
    }

    public void locale() {
        /* Locale */
        // 表示国家（或地区）和语言，两个参数，国家（或地区）和语言
        // 有一些默认的静态变量，如 Locale.CHINA、Locale.US、Locale.ENGLISH

        Locale aDefault = Locale.getDefault();
        System.out.println(aDefault.toString());

        Locale locale = Locale.SIMPLIFIED_CHINESE;
        System.out.println(locale.toString());
        Locale us = Locale.US;
        System.out.println(us.toString());

    }

    public void calender() {
        /* Calendar */
        // Calendar 是一个抽象类，不能直接实例化，可以通过 getInstance() 方法获取一个 Calendar 对象
        // 是日期和时间操作的主要类，提供了很多方法，如获取年、月、日、时、分、秒等
        // 表示与 TimeZone 和 Locale 相关的日历，可以将其转换为 Date 对象

        // 内部有一个字段，表示当前日历所表示的时间，是一个 long 类型的整数，表示自 1970 年 1 月 1 日 00:00:00 GMT 以来的毫秒数
        // 通过 getTimeInMillis() 方法获取这个字段的值
        // 还有一个数组，存储了当前日历所表示的时间的年、月、日、时、分、秒等信息，长度为 17，通过 get() 方法获取这个数组的值

        // Calendar 常用方法
        Calendar calendar = Calendar.getInstance();
        println("year ", calendar.get(Calendar.YEAR));
        println("month ", calendar.get(Calendar.MONTH));
        println("date ", calendar.get(Calendar.DATE));
        println("hour ", calendar.get(Calendar.HOUR));
        println("minute ", calendar.get(Calendar.MINUTE));
        println("second ", calendar.get(Calendar.SECOND));
        println("day of week ", calendar.get(Calendar.DAY_OF_WEEK));
        println("day of year ", calendar.get(Calendar.DAY_OF_YEAR));
        println("day of month ", calendar.get(Calendar.DAY_OF_MONTH));
        println("day of week in month ", calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));

        // 加减
        calendar.add(Calendar.DATE, 100);
        println("add 100 days ", calendar.get(Calendar.DATE));
        calendar.add(Calendar.DAY_OF_MONTH, -100);
        println("add -100 days ", calendar.get(Calendar.DATE));

        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"), Locale.US);
        println("year ", instance.get(Calendar.YEAR));
        println("month ", instance.get(Calendar.MONTH));
        println("date ", instance.get(Calendar.DATE));
        println("hour ", instance.get(Calendar.HOUR));
        println("minute ", instance.get(Calendar.MINUTE));
        println("second ", instance.get(Calendar.SECOND));
        println("day of week ", instance.get(Calendar.DAY_OF_WEEK));
        println("day of year ", instance.get(Calendar.DAY_OF_YEAR));
        println("day of month ", instance.get(Calendar.DAY_OF_MONTH));
        println("day of week in month ", instance.get(Calendar.DAY_OF_WEEK_IN_MONTH));
    }

    public void format() {

    }


    public void println(Object ...args) {
        // 连接成一个字符串打印出来
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
        System.out.println(stringBuilder);
    }
}
