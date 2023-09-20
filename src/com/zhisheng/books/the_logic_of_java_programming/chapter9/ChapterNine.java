package com.zhisheng.books.the_logic_of_java_programming.chapter9;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/9/14
 **/
public class ChapterNine {
    public static void main(String[] args) {
        ChapterNine chapterNine = new ChapterNine();
        chapterNine.ArrayListDemo();
    }

    private void ArrayListDemo() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        for (String string : strings) {
            System.out.println(string);
        }

        String remove = strings.remove(0);
    }
}
