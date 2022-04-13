package per.study.io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileWriterTest
{

    public static void main(String[] args) {
        // main方法：相较于当前工程
        File file = new File("hello.txt");
        // /Users/lrwei/lrwei/IdeaProjects/StudyJava/hello.txt
        System.out.println(file.getAbsoluteFile());
    }

    /**
     * int read()：返回读入的一个字符，如果达到文件末尾，返回-1
     * 异常处理：为了保证流资源一定可以执行关闭操作，需要使用try-catch-finally处理
     * 读入的文件一定要存在，否则就会报FileNotFoundException
     */
    @Test
    public void testFileReader() {
        FileReader fileReader = null;
        try {
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("1.txt");
            // 单元测试：相较于当前module（工程下的当前module）
            // System.out.println(file.getAbsoluteFile());

            // 2.提供具体的流
            fileReader = new FileReader(file);

            // 3.数据的读入
            // int read()：返回读入的一个字符，如果达到文件末尾，返回-1
            // 方式一
            /*
            int data = fileReader.read();
            while (data != -1) {
                System.out.print((char) data + "\t");
                data = fileReader.read();
            }
            */

            // 方式二：语法上针对方式一的修改
            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data + "\t");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // 4.流的关闭
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReader1() {
        FileReader fr = null;
        try {
            File file = new File("1.txt");
            fr = new FileReader(file);

            int len;
            char[] buf = new char[1024];
            while ((len = fr.read(buf)) != -1) {
                // 方式一
                /*
                for (int i=0; i < len; i++) {
                    System.out.println(buf[i]);
                }
                */

                // 方式二
                String str = new String(buf, 0, len);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 说明
     * 1.输出操作，对应的File可以不存在，并不会报异常
     * 2.
     *  File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件
     *  File对应的硬盘中的文件如果存在：
     *          如果流使用的构造器是：new FileWriter(file) / new FileWriter(file, false)：对原有文件的覆盖
     *          如果流使用的构造器是：new FileWriter(file, true)：不会对原有文件覆盖，而是在原有文件的基础上追加内容
     */
    @Test
    public void testFileWriter() {
        FileWriter  fw = null;
        try {
            File file = new File("2.txt");
            fw = new FileWriter(file, true);
            fw.write("I have a dream");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File srcFile = new File("1.txt");
            File destFile = new File("1-bak.txt");

            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] buff = new char[5];
            int len; // 每次读入到buff数组中的字符的个数
            while ((len = fr.read(buff)) != -1) {
                fw.write(buff, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
