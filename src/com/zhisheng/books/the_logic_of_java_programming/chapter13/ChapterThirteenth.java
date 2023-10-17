package com.zhisheng.books.the_logic_of_java_programming.chapter13;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
//        chapterThirteenth.byteArrayOutputStreamDemo();
//        List<Student> students = Arrays.asList(new Student("张三", 18, 80.9d),
//                new Student("李四", 17, 67.5d));
//        chapterThirteenth.dataOutputStreamDemo(students);
        chapterThirteenth.dataInputStreamDemo();
    }

    private void dataInputStreamDemo() throws IOException {
        DataInputStream input = new DataInputStream(new FileInputStream("student.dat"));
        try {
            int size = input.readInt();
            List<Student> students = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Student s = new Student();
                s.setName(input.readUTF());
                s.setAge(input.readInt());
                s.setScore(input.readDouble());
                students.add(s);
            }
            System.out.println(students);
        } finally {
            input.close();
        }
    }

    private void dataOutputStreamDemo(List<Student> studentList) throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream("student.dat"));
        try {
            output.writeInt(studentList.size());
            for (Student student : studentList) {
                output.writeUTF(student.getName());
                output.writeInt(student.getAge());
                output.writeDouble(student.getScore());
            }
        } finally {
            output.close();
        }
    }

    private static class Student {
        String name;
        int age;
        double score;

        public Student() {
        }

        public Student(String name, int age, double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", score=" + score +
                    '}';
        }
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
