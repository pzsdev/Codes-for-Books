package com.zhisheng.books.the_logic_of_java_programming.chapter17;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/8/9
 **/
public class C000CopyOnWriteArrayListDemo {
    List list = new CopyOnWriteArrayList();

    Set set = new CopyOnWriteArraySet();

    Map map = new ConcurrentHashMap();

}
