package per.study.io;

import org.junit.Test;

import java.io.*;

/**
 * 1. 缓冲流的使用
 *  BufferedReader
 *  BufferedWriter
 *  BufferedInputStream
 *  BufferedOutputStream
 * 
 * 2. 作用：提高流的读取，写入的时间
 */
public class BufferedTest
{

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        bufferedStreamTest("wp.jpg", "wpC.jpg");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public void bufferedStreamTest(String srcP, String destP) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File src = new File(srcP);
            File dest = new File(destP);
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            int len;
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                bos.flush(); // 刷新缓冲区
            }
            System.out.println("复制完成！！！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 先关闭外层流，再关闭内层流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 说明：关闭外层流的同时，内层流也会自动的进行关闭，关于内层流的关闭，可以省略
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void bufferedRW() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("1.txt"));
            bw = new BufferedWriter(new FileWriter("1-1.txt"));

            // 方式一：使用char[]数组
            /*
            int len;
            char[] cbuf = new char[1024];
            while((len = br.read(cbuf)) != -1) {
                bw.write(cbuf, 0, len);
            }
            */

            // 方式二：使用String
            String ret;
            while ((ret = br.readLine()) != null) {
                // 1
                // bw.write(ret + '\n'); // ret中不包含换行符
                // 2
                bw.write(ret);
                bw.newLine(); // '\n'
            }
            System.out.println("复制完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
