package com.zhisheng.books.ljp.c17;

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
