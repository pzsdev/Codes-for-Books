package com.zhisheng.corejava.priority;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * @program hello-world
 * @description: This program demonstrates the use of a priority queue.
 * @author: pengzhisheng
 * @create: 2019/10/30
 */
public class PriorityQueueTest {
    public static void main(String[] args){
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 13));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements ...");
        for (LocalDate date : pq){
            System.out.println(pq);
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
