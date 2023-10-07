package com.zhisheng.books.the_logic_of_java_programming.chapter11;

import java.util.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/7
 **/
public class ChapterEleven {
    public static void main(String[] args) {
        ChapterEleven chapterEleven = new ChapterEleven();
//        chapterEleven.PriorityQueueDemo();
//        chapterEleven.taskQueueDemo();
        chapterEleven.topKDemo();
    }

    private void PriorityQueueDemo() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(10);
        queue.add(22);
        queue.addAll(Arrays.asList(11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13));

        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }

        System.out.println("--------");
        // reverse
        Queue<Integer> queue1 = new PriorityQueue<>(11, Collections.reverseOrder());
        queue1.offer(10);
        queue1.add(22);
        queue1.addAll(Arrays.asList(11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13));

        while (queue1.peek() != null) {
            System.out.println(queue1.poll());
        }
    }

    private class Task {
        int priority;
        String name;

        public Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static Comparator<Task> priorityComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1.getPriority() > o2.getPriority()) {
                return -1;
            } else if (o1.getPriority() < o2.getPriority()) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    private void taskQueueDemo() {
        Queue<Task> tasks = new PriorityQueue<>(11, priorityComparator);

        tasks.offer(new Task(20, "写日记"));
        tasks.offer(new Task(10, "看电视"));
        tasks.offer(new Task(100, "写代码"));
        Task task = tasks.poll();
        while (task != null) {
            System.out.println("处理任务：" + task.getName() + ", 优先级：" + task.getPriority());
            task = tasks.poll();
        }
    }

    /**
     * 求前 K 个最大的元素
     * @param <E>
     */
    private class TopK<E> {
        private PriorityQueue<E> p;
        private int k;
        public TopK(int k){
            this.k = k;
            p = new PriorityQueue<>(k);
        }

        public void addAll(Collection<E> e){
            for (E e1 : e) {
                add(e1);
            }
        }

        public void add(E e) {
            if (p.size() < k) {
                p.add(e);
                return;
            }

            Comparable<? super E> head = (Comparable<? super E>) p.peek();
            if (head.compareTo(e) > 0) {
                // 小于 TopK 中的最小值，不用变
                return;
            }
            // 新元素替换掉原来的最小值成为 TopK 之一
            p.poll();
            p.add(e);
        }

        public <T> T[] toArray(T[] a) {
            return p.toArray(a);
        }

        public E getKth(){
            return p.peek();
        }
    }

    /**
     * 求前 K 个最大的元素demo
     */
    private void topKDemo() {
        TopK<Integer> topK = new TopK<>(5);
        topK.addAll(Arrays.asList(new Integer[]{
                100, 1, 2, 6, 34, 9, 23, 21, 99, 0
        }));
        System.out.println(Arrays.toString(topK.toArray(new Integer[0])));
        System.out.println(topK.getKth());
    }
}
