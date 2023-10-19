package com.zhisheng.books.the_logic_of_java_programming.chapter14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/19
 **/
public class ChapterFourteenth {
    public static void main(String[] args) throws IOException {
        ChapterFourteenth fourteenth = new ChapterFourteenth();
        fourteenth.propertiesDemo();
    }

    /**
     * 获取配置文件配置字段
     * @throws IOException
     */
    private void propertiesDemo() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String host = prop.getProperty("db.host");
        Integer port = Integer.valueOf(prop.getProperty("db.port", "3306"));
        System.out.println("host = " + host);
        System.out.println("port = " + port);
    }
}
