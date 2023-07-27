package com.zhisheng.books.the_logic_of_java_programming.c2;

import java.io.UnsupportedEncodingException;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2022/7/22
 **/
public class BasicTypeCoding {

    public static void print(String string) {
        System.out.println(string);
    }

    public static void print(String string, Object obj) {
        System.out.println(string + ": " + obj);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 查看整数的二进制和十六进制表示
        print("查看整数的二进制和十六进制表示");

        int a = 25;
        print("origin int", ((Integer)a).toString());
        print("binary", Integer.toBinaryString(a));
        print("hex", Integer.toHexString(a));
        print("binary", Long.toBinaryString(a));
        print("hex", Long.toHexString(a));
        print("---------");

        print("位运算");
        int i = 4;
        print("原始为", i);
        print("binary", Integer.toBinaryString(i));

        i = i >> 2;
        print("有符号右移", i);
        print("binary", Integer.toBinaryString(i));

        i = i << 3;
        print("左移", i);
        print("binary", Integer.toBinaryString(i));
        print("---------");

        print("浮点数的二进制表示");
        float f = 1.234f;
        print("origin float", ((Float)f).toString());
        String floatBinaryString = Integer.toBinaryString(Float.floatToIntBits(f));
        print("binary", floatBinaryString);
        print("binary length is ", floatBinaryString.length());

        float f2 = -1.234f;
        print("origin float", ((Float)f2).toString());
        String floatBinaryString2 = Integer.toBinaryString(Float.floatToIntBits(f2));
        print("binary", floatBinaryString2);
        print("binary length is ", floatBinaryString2.length());


        double d = 1.234d;
        print("origin double ", ((Double)d).toString());
        String doubleBinaryString = Long.toBinaryString(Double.doubleToLongBits(d));
        print("binary", doubleBinaryString);
        print("binary length is ", doubleBinaryString.length());

        double d2 = -1.234d;
        print("origin double ", ((Double)d2).toString());
        String doubleBinaryString2 = Long.toBinaryString(Double.doubleToLongBits(d2));
        print("binary", doubleBinaryString2);
        print("binary length is ", doubleBinaryString2.length());

        print("---------");

        print("char 二进制表示");
        char c = '彭';
        print("origin char", c);
        print("binary", Integer.toBinaryString(c));
        print("binary", Long.toBinaryString(c));
        print("---------");

        print("Java 编码与乱码");
        String str = "ÀÏÂí";
        print("origin ", str);
        String newStr = new String(str.getBytes("windows-1252"), "GB18030");
        print("转码恢复后的", newStr);

        recover(str);
    }

    public static void recover(String str) throws UnsupportedEncodingException{
        String[] charsets = new String[]{"windows-1252", "GB18030", "Big5", "UTF-8"};

        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                if (i != j) {
                    String s = new String(str.getBytes(charsets[i]), charsets[j]);
                    print("原来的编码（A）假设是：" + charsets[j] + "，被错误解读为（B）：" + charsets[i]);
                    print(s);
                    print("---");
                }
            }
        }
    }
}
