package com.zhisheng.books.the_logic_of_java_programming.c7;

import java.nio.charset.Charset;
import java.util.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/7/27
 **/
public class ChapterSeven {
    public static void main(String[] args) {
//        System.out.println("Chapter Seven");

        ChapterSeven chapterSeven = new ChapterSeven();
//        chapterSeven.date();
//        chapterSeven.timeZone();
//        chapterSeven.locale();
//        chapterSeven.calender();

//        chapterSeven.integer();
//        chapterSeven.charsetDemo();
//        chapterSeven.randomDemo();
//        chapterSeven.randomPassword();
//        chapterSeven.randomPassword2();
//        chapterSeven.poker();
        chapterSeven.randomWithWeight();
    }

    // 带权重的随机选择
    /**
     * 表示选项和权重
     */
    class Pair {
        Object item;
        int weight;
        public Pair(Object item, int weight) {
            this.item = item;
            this.weight = weight;
        }

        public Object getItem() {
            return item;
        }
        public int getWeight() {
            return weight;
        }
    }

    class WeightRandom {
        private Pair[] options;
        private double[] cumulativeProbabilities;
        private Random rnd;

        public WeightRandom(Pair[] options) {
            this.options = options;
            this.rnd = new Random();
            prepare();
        }

        // 计算每个选项的累计概率，保存在 cumulativeProbabilities 中
        private void prepare() {
            // 总权重
            int weights = 0;
            for (Pair option : options) {
                weights += option.getWeight();
            }

            // 累计概率，这个是理解难点
            this.cumulativeProbabilities = new double[options.length];
            // 累计权重
            int sum = 0;
            for (int i = 0; i < options.length; i++) {
                sum += options[i].getWeight();
                this.cumulativeProbabilities[i] = sum / (double)weights;
            }
        }

        public Object nextItem() {
            double rand = rnd.nextDouble();
            int index = Arrays.binarySearch(cumulativeProbabilities, rand);
            if (index < 0) {
                // 没找到，返回结果是 -（插入点） -1，经过下面的转换得到的就是插入点了
                index = -(index + 1);
            }
            return options[index].getItem();
        }
    }

    private void randomWithWeight() {
        Pair[] options = new Pair[]{
                new Pair("1元", 7), new Pair("2元", 2), new Pair("10元", 1)
        };

        WeightRandom random = new WeightRandom(options);

        System.out.println(Arrays.toString(random.cumulativeProbabilities));
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextItem());
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 从后往前，逐个给每个数组位置重新复制，值是从剩下的元素中随机挑选的
    private void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length; i > 1; i--) {
            // i-1 表示当前要赋值的位置，rnd.nextInt(i)表示从剩下的元素中随机挑选
            swap(arr, i - 1, rnd.nextInt(i));
        }
    }

    /**
     * 随机场景-洗牌
     */
    private void poker() {
        int[] arr = new int[13];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }


    // 随机生成一个未赋值的位置
    // char默认值为’\u0000’是个空格，转化为整数是0
    private int nextIndex(char[] chars, Random rnd) {
        int index = rnd.nextInt(chars.length);
        while (chars[index] != 0) {
            index = rnd.nextInt(chars.length);
        }
        return index;
    }

    private char nextSpecialChar(Random rnd) {
        return SPEIAL_CHARS.charAt(rnd.nextInt(SPEIAL_CHARS.length()));
    }

    private char nextUpperLetter(Random rnd) {
        return (char) (rnd.nextInt(26) + 'A');
    }

    private char nextLowerLetter(Random rnd) {
        return (char) (rnd.nextInt(26) + 'a');
    }

    private char nextNumLetter(Random rnd) {
        return (char) (rnd.nextInt(10) + '0');
    }

    // 随机生成一个8位的字符串，至少包含一个特殊字符，一个大写字符，一个小写字符，一个数字
    private void randomPassword2() {
        char[] chars = new char[8];
        Random rnd = new Random();
        chars[nextIndex(chars, rnd)] = nextSpecialChar(rnd);
        chars[nextIndex(chars, rnd)] = nextUpperLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextLowerLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextNumLetter(rnd);
        for (int i = 0; i < 8; i++) {
            if (chars[i] == 0) {
                chars[i] = nextChar(rnd);
            }
        }
        System.out.println(new String(chars));
    }

    // 八位随机密码
    public void randomPassword() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        System.out.println(new String(chars));
    }

    private static final String SPEIAL_CHARS = "!@#$%^&*()_+";
    private char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return SPEIAL_CHARS.charAt(rnd.nextInt(SPEIAL_CHARS.length()));
            case 1:
                return (char) (rnd.nextInt(26) + 'a');
            case 2:
                return (char) (rnd.nextInt(26) + 'A');
            default:
                return (char) (rnd.nextInt(10) + '0');
        }
    }


    public void randomDemo() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Math.random());
        }

        // 可复现的随机
        Random random = new Random(12341242341234L);
        for (int i = 0; i < 3; i++) {
            System.out.println(random.nextDouble());
        }

    }

    public void charsetDemo() {
        // 返回系统默认编码
        System.out.println(Charset.defaultCharset().name());

        // 返回给定编码名称的Charset对象，其charsetName 名称可以是US-ASCII、ISO-8859-1、windows-1252、GB2312、GBK、GB18030、Big5、UTF-8等
        Charset charset = Charset.forName("GB18030");
        System.out.println(charset.name());

    }

    public void integer() {
        int a = 0x12345678;
        System.out.println(Integer.toBinaryString(a));

        int r = Integer.reverse(a);
        System.out.println(Integer.toBinaryString(r));

        int rb = Integer.reverseBytes(a);
        System.out.println(Integer.toHexString(rb));

        //    10010 00110100 01010110 01111000
        //000010010 00110100 01010110 01111000
        //    11110 01101010 00101100 01001000
        //000011110 01101010 00101100 01001000
        //78563412

        // 补足 int 的 32 位，就可以看出是前后交换
        //000010010 00110100 01010110 01111000
        //000011110 01101010 00101100 01001000
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
