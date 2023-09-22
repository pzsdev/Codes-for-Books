package com.zhisheng.books.the_logic_of_java_programming.chapter9;

import java.util.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/9/14
 **/
public class ChapterNine {
    public static void main(String[] args) {
        ChapterNine chapterNine = new ChapterNine();
//        chapterNine.arrayListDemo();
//        chapterNine.linkedListDemo();
        chapterNine.arrayDequeDemo();
    }

    private void arrayListDemo() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        for (String string : strings) {
            System.out.println(string);
        }

        String remove = strings.remove(0);
    }

    private void linkedListDemo() {
        List<Object> list = new LinkedList<>();
        List<String> list2 = new LinkedList<>(Arrays.asList("a", "b", "c"));
        for (String string : list2) {
            System.out.println(string);
        }

        // Queue
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }

        // stack
        Deque<String> stack = new LinkedList<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        while (stack.peek() != null) {
            System.out.println(stack.pop());
        }
    }

    private void arrayDequeDemo() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        ArrayDeque<String> deque2 = new ArrayDeque<>(3);
        deque2.offer("a");
        deque2.offer("b");
        deque2.offer("c");
        while (deque2.peek() != null) {
            System.out.println(deque2.poll());
        }
    }
}
