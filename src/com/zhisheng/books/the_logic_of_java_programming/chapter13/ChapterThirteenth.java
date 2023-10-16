package com.zhisheng.books.the_logic_of_java_programming.chapter13;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/16
 **/
public class ChapterThirteenth {
    public static void main(String[] args) throws IOException {
        ChapterThirteenth chapterThirteenth = new ChapterThirteenth();
        chapterThirteenth.fileOutputStreamDemo();
//        chapterThirteenth.fileInputStreamDemo();
        chapterThirteenth.byteArrayOutputStreamDemo();
    }

    private void fileOutputStreamDemo() throws IOException {
//        OutputStream output = new FileOutputStream("hello.txt");
        OutputStream output = new FileOutputStream("hello.txt", true);
        try {
            String data = "hello, 123, 老彭。 ";
//            String data = "hello, 123, 老彭。 \n";
            byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            output.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            output.close();
        }
    }

    private void fileInputStreamDemo() throws IOException {
        InputStream input = new FileInputStream("hello.txt");
        try {
            byte[] buf = new byte[500];
            int bytesRead = input.read(buf); // 只能一次性读取一个内容长度不超过这个字节数组长度的数据
            String data = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
            System.out.println("read size is " + bytesRead);
            System.out.println(data);
        } finally {
            input.close();
        }
    }

    private void byteArrayOutputStreamDemo() throws IOException {
        InputStream input = new FileInputStream("hello.txt");
        try {
            // byteArrayOutputStream 的输出目标是一个 byte 数组，这个数组的长度是根据数据内容动态扩展的
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buf = new byte[500];
            int bytesRead;
            // 输入的数据线写入 ByteArrayOutputStream 中，读完后，再调用其 toString 方法获取完整数据
            while ((bytesRead = input.read(buf)) != -1) {
                System.out.println("read size is " + bytesRead);
                output.write(buf, 0, bytesRead); // 这个 off 是 待写入数据 buf 的起始位置
            }
            String data = output.toString("UTF-8");
            System.out.println("read total size is " + output.size());
            System.out.println(data);
        } finally {
            input.close();
        }
    }
}
