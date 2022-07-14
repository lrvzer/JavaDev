package per.study.io;

import org.junit.Test;

import java.io.*;

/**
 * RandomAccessFile使用 1.RandomAccessFile直接继承于java.lang.Object类，实现了接口DataOutput,
 * DataInput 2.既可以作为输入流，又可以作为输出流 3.作为输出流时，写出的文件如果不存在，则在执行过程中自动创建；
 * 如果存在，则对原有文件内容进行覆盖（默认情况下，从头覆盖） 4.可以通过相关操作，实现"插入"数据的效果
 */
public class RandomAccessFileTest
{

    @Test
    public void test() {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile("wallpaper.jpg", "r");
            raf2 = new RandomAccessFile("wallpaper.cpy.jpg", "rw");

            int len;
            byte[] buf = new byte[1024];
            while ((len = raf1.read(buf)) != -1) {
                raf2.write(buf, 0, len);
            }
            System.out.println("over");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (raf1 != null) {
                try {
                    raf1.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null) {
                try {
                    raf2.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hello.txt", "rw");
        raf.seek(3); // 将指针指向第4个位置
        raf.write("xyz".getBytes());
        raf.close();
    }

    /**
     * 插入效果
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hello.txt", "rw");
        raf.seek(3); // 将指针指向第4个位置
        /*
         * StringBuilder builder = new StringBuilder((int) new
         * File("hello.txt").length()); int len; byte[] buf = new byte[20]; while ((len
         * = raf.read(buf)) !=-1) { builder.append(new String(buf, 0, len)); }
         */

        // 方法二：ByteArrayOutputStream
        // 字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buf = new byte[1024];
        while ((len = raf.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        raf.seek(3);
        raf.write("xyz".getBytes());
        raf.write(baos.toByteArray());
        raf.close();
    }

    @Test
    public void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hello.txt", "rw");
        int maxSize = 8;
        long offset = 0L;
        for (int i = 1; i <= 5; i++) {

            long start = offset;
            System.out.print("start: " + start);
            long end = i * maxSize;
            System.out.print(" end: " + end);
            int len;
            byte[] buf = new byte[2];

            raf.seek(start);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            while ((len = raf.read(buf)) != -1 && raf.getFilePointer() <= end) {
                baos.write(buf, 0, len);
            }
            offset = raf.getFilePointer() - 2;
            System.out.println(" pointer: " + raf.getFilePointer());
            System.out.println(baos);
            baos.close();
        }
        raf.close();
    }

}
