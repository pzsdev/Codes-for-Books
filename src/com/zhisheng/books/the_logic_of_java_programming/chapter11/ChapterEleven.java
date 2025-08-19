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

        // 基本优先队列
//        chapterEleven.PriorityQueueDemo();

        // 有优先级的任务队列
//        chapterEleven.taskQueueDemo();

        // 输出
//        chapterEleven.topKDemo();

        // 输出中位数
        chapterEleven.getMedian();
    }

    private void getMedian() {
        Median<Integer> median = new Median<>();
        median.add(1);
        median.add(2);
        median.add(3);

        median.add(31);
        median.add(32);
        median.add(33);
        median.add(5);
        System.out.println(median.getM());
    }

    /**
     * 求利用最大堆/最小堆求中值
     * @param <E>
     */
    private class Median<E> {
        private PriorityQueue<E> minP; // 最小堆
        private PriorityQueue<E> maxP; // 最大堆
        private E m; // 中位数

        public Median() {
            minP = new PriorityQueue<>();
            maxP = new PriorityQueue<>(11, Collections.reverseOrder());
        }

        private int compare(E e, E m) {
            Comparable<? super E> cmpr = (Comparable<? super E>) e;
            return cmpr.compareTo(m);
        }

        public void add(E e) {
            if (m == null) {
                m = e;
                return;
            }

            if (compare(e, m) <= 0) {
                // 小于中值，加入最大堆
                maxP.add(e);
            } else {
                minP.add(e);
            }

            if (minP.size() - maxP.size() >= 2) {
                // 最小堆元素个数多，即大于当前中值的数多
                // 将 m 加入到最大堆中，然后将最小堆中的根移除赋值给m
                maxP.add(m);
                this.m = minP.poll();
            } else if (maxP.size() - minP.size() >= 2) {
                // 最大堆元素个数多，即小于当前中值的数多
                // 将 m 加入到最小堆中，然后将最大堆中的根移除赋值给m
                minP.add(m);
                this.m = maxP.poll();
            }
        }

        public void addAll(Collection<? extends E> c){
            for (E e : c) {
                add(e);
            }
        }

        public E getM() {
            return m;
        }
    }

    private void PriorityQueueDemo() {
        Queue<Integer> queue = new PriorityQueue<>();

        queue.offer(10); // offer 队尾添加，满了返回 false
        queue.add(22); // add 尾部添加，满了扔异常

        queue.addAll(Arrays.asList(11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13));

        // 遍历队列
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

    // 任务优先队列
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
        tasks.offer(new Task(110, "去自习室"));

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
            // 没超过 k 个，直接添加进去
            if (p.size() < k) {
                p.add(e);
                return;
            }

            Comparable<? super E> head = (Comparable<? super E>) p.peek();
            if (head.compareTo(e) > 0) {
                // 小于 TopK 中的最小值，不用变
                return;
            }
            // 大于等于 最小值，新元素替换掉原来的最小值成为 TopK 之一
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
