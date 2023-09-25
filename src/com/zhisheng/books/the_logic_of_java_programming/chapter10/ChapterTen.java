package com.zhisheng.books.the_logic_of_java_programming.chapter10;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/9/24
 **/
public class ChapterTen {
    public static void main(String[] args) {
        ChapterTen chapterTen = new ChapterTen();
        chapterTen.hashMapDemo();
    }

    private void hashMapDemo() {
        Random random = new Random();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int num = random.nextInt(4);
            Integer count = countMap.get(num);
            countMap.put(num, count == null ? 1 : count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
