package com.zhisheng.books.the_logic_of_java_programming.chapter13;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * TODO
 *
 * @author pengzhisheng
 * @since 2023/10/16
 **/
public class ChapterThirteenth {
    public static void main(String[] args) throws IOException {
        ChapterThirteenth chapterThirteenth = new ChapterThirteenth();
//        chapterThirteenth.fileOutputStreamDemo();
//        chapterThirteenth.fileInputStreamDemo();
//        chapterThirteenth.byteArrayOutputStreamDemo();
//        chapterThirteenth.printUseDir();
//        List<Student> students = Arrays.asList(new Student("张三", 18, 80.9d),
//                new Student("李四", 17, 67.5d));
//        chapterThirteenth.dataOutputStreamDemo(students);
//        chapterThirteenth.dataInputStreamDemo();
//        chapterThirteenth.fileDemo();
//        chapterThirteenth.fileMangeDemo();
//        chapterThirteenth.fileFilterDemo();
        chapterThirteenth.getSizeOfDir();
    }

    /**
     * 删除非空目录
     * @param file
     * @throws IOException
     */
    private static void deleteRecursively(File file) throws IOException {
        if (file.isFile()) {
            if (!file.delete()) {
                throw new IOException("Failed to deleted " + file.getCanonicalPath());
            }
        } else if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteRecursively(f);
            }
            if (!file.delete()) {
                throw new IOException("Failed to deleted " + file.getCanonicalPath());
            }
        }
    }

    /**
     * 在一个目录下，查找所有给定文件名的文件
     * @param dir
     * @param fileName
     * @return
     */
    private static Collection<File> findFile(final File dir, final String fileName) {
        List<File> files = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getName().equals(fileName)) {
                files.add(file);
            } else if (file.isDirectory()) {
                files.addAll(findFile(file, fileName));
            }
        }
        return files;
    }

    private void getSizeOfDir() {
        long l = ChapterThirteenth.sizeOfDir(new File("/Users/pengzhisheng/git-repository/Codes-for-Books"));
        System.out.println(l);
    }

    /**
     * 计算一个目录下所有文件的大小（包括子目录）
     * @param dir
     * @return
     */
    private static long sizeOfDir(File dir) {
        long size = 0;
        if (dir.isFile()) {
            return dir.length();
        } else {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += sizeOfDir(file);
                }
            }
        }
        return size;
    }

    private void fileFilterDemo() {
        File file = new File("/Users/pengzhisheng/git-repository/Codes-for-Books");
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt")) {
                    return true;
                }
                return false;
            }
        });

        System.out.println(Arrays.toString(list));
    }

    private void fileMangeDemo() throws IOException {
        File file = new File("hello.txt");
        System.out.println("create " + file.createNewFile());

        File file1 = new File("helloWorld.txt");
        System.out.println("create " + file1.createNewFile());

        File helloTemp = File.createTempFile("helloTemp", ".txt");
        System.out.println("exist " + helloTemp.exists());
        System.out.println("canonical path " + helloTemp.getCanonicalPath());

        File helloTemp2 = File.createTempFile("helloTemp", null);
        System.out.println("exist " + helloTemp2.exists());
        System.out.println("canonical path " + helloTemp2.getCanonicalPath());

        System.out.println("exist " + new File("/private/var/folders/p9/hhkdc1rx7z9_xd04bjsj_hsh0000gn/T/helloTemp5926274146984628250.txt").exists());

        File helloTemp1 = File.createTempFile("helloTemp", ".txt", new File("/Users/pengzhisheng"));
        System.out.println("exist " + helloTemp1.exists());
        System.out.println("canonical path " + helloTemp1.getCanonicalPath());

        System.out.println("delete " + helloTemp1.delete());
        System.out.println("deleteOnExit exec");
        helloTemp1.deleteOnExit();
        System.out.println("exist " + helloTemp1.exists());

        System.out.println("rename " + file1.renameTo(new File("helloWorld1.txt")));
    }

    private void fileDemo () throws IOException {
        File file = new File("hello.txt");
//        File file = new File("/Users/pengzhisheng/git-repository/Codes-for-Books/hello.txt");
//        File file = new File("/Users/pengzhisheng/../pengzhisheng/git-repository/Codes-for-Books/hello.txt");
        System.out.println("exist " + file.exists());
        System.out.println("name " + file.getName());
        System.out.println("path " + file.getPath());// 构造File时的完整路径名
        System.out.println("is absolute " + file.isAbsolute());
        System.out.println("absolute path " + file.getAbsolutePath());
        System.out.println("canonical path " + file.getCanonicalPath()); // 获取绝对路径
        System.out.println("parent " + file.getParent()); // 构造 File 时的路径上的父路径
        System.out.println("parent file " + file.getParentFile());
        System.out.println("absolute file " + file.getAbsoluteFile());
        System.out.println("canonical file " + file.getCanonicalFile());

        System.out.println("");
        System.out.println("File separator " + File.separator); // 文件路径分隔符
        System.out.println("File separator char " + File.separatorChar);
        System.out.println("Path separator " + File.pathSeparator); // 多个文件路径中的分隔符，例如 PATH 中的分隔符
        System.out.println("Path separator char " + File.pathSeparatorChar);

        System.out.println("");
        System.out.println("is directory " + file.isDirectory());
        System.out.println("is file " + file.isFile());
        System.out.println("length " + file.length()); // 文件字节数
        System.out.println("last modified " + new Date(file.lastModified()));
        System.out.println("set modified " + file.setLastModified(System.currentTimeMillis()));
        System.out.println("new last modified " + new Date(file.lastModified()));

        // 安全相关
        System.out.println("");
        System.out.println("is hidden " + file.isHidden());
        System.out.println("can read " + file.canRead());
        System.out.println("can write " + file.canWrite());
        System.out.println("can execute " + file.canExecute());
        System.out.println("set readOnly " + file.setReadOnly());

//        System.out.println("update readable " + file.setReadable(true));
        // ownerOnly 为 true，表示只针对 owner， 为 false，表示针对所有用户
        System.out.println("update readable " + file.setReadable(true, false));
        System.out.println("set read " + file.canRead());

        System.out.println("update writable " + file.setWritable(true));
        System.out.println("set write " + file.canWrite());

        System.out.println("update executable " + file.setExecutable(true));
        System.out.println("can execute " + file.canExecute());
        System.out.println("update executable " + file.setExecutable(false));
    }

    /**
     * 按行将文件内容读到一个列表中
     * @param fileName
     * @param encoding
     * @return
     * @throws IOException
     */
    public static List<String> readLinesFromFile(final String fileName, final String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
            List<String> lines = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            return lines;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 按行将多行数据写到文件
     * @param fileName
     * @param encoding
     * @param lines
     * @throws IOException
     */
    public static void writeLinesToFile(final String fileName, final String encoding, final Collection<?> lines) throws IOException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, encoding);
            for (Object line : lines) {
                writer.println(line);
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 将字符串写到文件
     * @param fileName
     * @param data
     * @param encoding
     * @throws IOException
     */
    public static void writerStringToFile(final String fileName, final String data, final String encoding) throws IOException {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), encoding));
            writer.write(data);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 读取文件内容到一个字符串
     * @param fileName
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String readFileToString(final String fileName, final String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
            StringWriter writer = new StringWriter();
            copy(reader, writer);
            return writer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    /**
     * 复制 输入字符流的内容到输出字符流
     * @param input
     * @param output
     * @throws IOException
     */
    public static void copy(final Reader input, final Writer output) throws IOException {
        char[] cbuf = new char[1024];
        int len;
        while ((len = input.read(cbuf)) != -1) {
            output.write(cbuf, 0, len);
        }
    }

    /**
     * 将文件读入字节数组
     * @param fileName
     * @return
     * @throws IOException
     */
    private static byte[] readFileToByteArray(String fileName) throws IOException {
        InputStream input = new FileInputStream(fileName);
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            copy(input, output);
            return output.toByteArray();
        } finally {
            input.close();
        }
    }

    /**
     * 复制输入流的内容到输出流
     * @param input
     * @param output
     * @throws IOException
     */
    private static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[4096];
        int bytesRead;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
    }

    /**
     * 将字节数组写到文件
     * @param fileName
     * @param bytes
     * @throws IOException
     */
    private static void writeByteToFile(String fileName, byte[] bytes) throws IOException {
        OutputStream output = new FileOutputStream(fileName);
        try {
            output.write(bytes);
        } finally {
            output.close();
        }
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

    /**
     * 得到运行Java程序的当前目录
     */
    private void printUseDir() {
        System.out.println(System.getProperty("user.dir"));

        // 获取程序系统属性
        String propertiesStr = System.getProperties().toString();
        propertiesStr = propertiesStr.replaceAll(",", ",\n");
        System.out.println(propertiesStr);
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
