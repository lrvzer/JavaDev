package per.study.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * 1.转换流
 *  InputStreamReader：将一个字节的输入流转化为字符的输入流
 *  OutputStreamWriter：将一个字符的输出流转化为字节的输出流
 *
 * 2.作用
 *  提供和字节流和字符流之间的转换
 *
 * 3.
 *  解码：字节、字节数组    --> 字符数组、字符串
 *  编码：字符数组、字符串  --> 字节、字节数组
 *
 * 4.字符集
 *  ASCII
 *  ISO8859-1
 *  GB2312
 *  GBK
 *  Unicode
 *  UTF-8
 */
public class InputStreamReaderTest {

    @Test
    public void test1() {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream("1.txt"), StandardCharsets.UTF_8);
            int len;
            char[] cbuf = new char[1024];
            while ((len = isr.read(cbuf)) != -1) {
                String ret = new String(cbuf, 0, len);
                System.out.println(ret);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() {
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            isr = new InputStreamReader(new FileInputStream("1.txt"), StandardCharsets.UTF_8);
            osw = new OutputStreamWriter(new FileOutputStream("1-1.txt"), "gbk");

            int len;
            char[] cbuf = new char[1024];
            while ((len = isr.read(cbuf)) != -1) {
                osw.write(cbuf, 0, len);
            }
            System.out.println("over!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
