package com.zhisheng.books.the_logic_of_java_programming.chapter10;

import java.util.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/9/24
 **/
public class ChapterTen {
    public static void main(String[] args) {
        ChapterTen chapterTen = new ChapterTen();
//        chapterTen.hashMapDemo();
//        chapterTen.hashSetDemo();
//        chapterTen.treeMapDemo();
//        chapterTen.treeSetDemo();
//        chapterTen.linkedHashMapDemo();
//        chapterTen.lruCacheDemo();
        chapterTen.enumMapDemo();
    }

    private enum Size {
        SMALL, MEDIUM, LARGE
    }

    private class Clothes {
        String id;
        Size size;

        public Clothes(String id, Size size) {
            this.id = id;
            this.size = size;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Size getSize() {
            return size;
        }

        public void setSize(Size size) {
            this.size = size;
        }
    }

    private void enumMapDemo() {
        List<Clothes> clothes = Arrays
                .asList(new Clothes[]{new Clothes("1", Size.SMALL),
                        new Clothes("2", Size.MEDIUM),
                        new Clothes("3", Size.LARGE),
                        new Clothes("4", Size.SMALL),
                        new Clothes("5", Size.MEDIUM),
                        new Clothes("6", Size.LARGE)}
                );
        System.out.println(countBySize(clothes));
    }

    private Map<Size, Integer> countBySize(List<Clothes> clothes) {
        Map<Size, Integer> map = new EnumMap<>(Size.class);
        for (Clothes c : clothes) {
            Size size = c.getSize();
            map.merge(size, 1, Integer::sum);
        }
        return map;
    }

    private void lruCacheDemo() {
        LRUCache<Object, Object> lruCache = new LRUCache<>(3);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        lruCache.get("a");
        lruCache.put("d", 4);
        System.out.println(lruCache);
    }

    private class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private int maxEntries;

        public LRUCache(int maxEntries) {
            super(16, 0.75f, true);
            this.maxEntries = maxEntries;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxEntries;
        }
    }


    private void linkedHashMapDemo() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a", 1);
        linkedHashMap.put("b", 2);
        linkedHashMap.put("c", 3);
        linkedHashMap.put("d", 4);
        linkedHashMap.put("e", 5);
        linkedHashMap.put("f", 6);
        linkedHashMap.put("g", 7);
        linkedHashMap.put("h", 8);
        linkedHashMap.put("i", 9);
        linkedHashMap.put("j", 10);
        linkedHashMap.put("k", 11);
        linkedHashMap.put("l", 12);
        linkedHashMap.put("m", 13);
        linkedHashMap.put("n", 14);
        linkedHashMap.put("o", 15);
        linkedHashMap.put("p", 16);
        linkedHashMap.put("q", 17);
        linkedHashMap.put("r", 18);
        linkedHashMap.put("s", 19);

        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry);
        }

        linkedHashMap.remove("a");
    }

    private void treeSetDemo() {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("a");
        treeSet.add("b");
        treeSet.add("c");
        treeSet.add("d");
        treeSet.add("e");
        treeSet.add("f");
        treeSet.add("g");
        treeSet.add("h");
        treeSet.add("i");
        treeSet.add("j");
        treeSet.add("k");
        treeSet.add("l");
        treeSet.add("m");
        treeSet.add("n");
        treeSet.add("o");
        treeSet.add("p");
        treeSet.add("q");
        treeSet.add("r");
        treeSet.add("s");

        for (String string : treeSet) {
            System.out.println(string);
        }
    }

    private void treeMapDemo() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("a", 1);
        treeMap.put("b", 2);
        treeMap.put("c", 3);
        treeMap.put("d", 4);
        treeMap.put("e", 5);
        treeMap.put("f", 6);
        treeMap.put("g", 7);
        treeMap.put("h", 8);
        treeMap.put("i", 9);
        treeMap.put("j", 10);
        treeMap.put("k", 11);
        treeMap.put("l", 12);
        treeMap.put("m", 13);
        treeMap.put("n", 14);
        treeMap.put("o", 15);
        treeMap.put("p", 16);
        treeMap.put("q", 17);
        treeMap.put("r", 18);
        treeMap.put("s", 19);

        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    private void hashSetDemo() {
        HashSet<String> hashSet = new HashSet<>();
        boolean a = hashSet.add("a");
        boolean a1 = hashSet.add("a");

        System.out.println(a);
        // 只有在不存在时，才是添加，并返回 true
        System.out.println(a1);
        for (String string : hashSet) {
            System.out.println(string);
        }
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
