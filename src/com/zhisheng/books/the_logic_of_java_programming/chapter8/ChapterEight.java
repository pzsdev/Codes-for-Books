package com.zhisheng.books.the_logic_of_java_programming.chapter8;

public class ChapterEight {
    public static void main(String[] args) {

    }

    public static int indexOf(DynamicArray<?> arr, Object elm) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(elm)) {
                return i;
            }
        }

        return -1;
    }


    public static <T> int indexOfTwo(DynamicArray<T> arr, Object elm) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(elm)) {
                return i;
            }
        }

        return -1;
    }
}
