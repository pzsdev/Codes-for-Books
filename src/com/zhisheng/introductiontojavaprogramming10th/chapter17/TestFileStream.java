package com.zhisheng.introductiontojavaprogramming10th.chapter17;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The type Test file stream.
 */
public class TestFileStream {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        File tempFile = new File("src/com/zhisheng/introductiontojavaprogramming10th/staticFile/temp.dat");
        String path = tempFile.getAbsolutePath();
        System.out.println(path);
        System.out.println(tempFile.exists());

        try (
                FileOutputStream output = new FileOutputStream(tempFile);
        ){
            for (int i = 1; i < 10; i++) {
                output.write(i);
            }
        }

        try (
                FileInputStream input = new FileInputStream(tempFile);
        ) {
            int value;
            while ((value = input.read()) != -1) {
                System.out.println(value + " ");
            }
        }


    }
}
