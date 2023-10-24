package com.zhisheng.books.the_logic_of_java_programming.chapter15;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/24
 **/
public interface MyFuture<V> {
    V get() throws Exception;
}
