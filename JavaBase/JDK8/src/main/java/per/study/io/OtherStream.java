package per.study.io;

import org.junit.Test;

import java.io.*;

public class OtherStream {

    /**
     * 1.
     *  System.in：标准的输入流，默认从键盘输入
     *  System.out：标准的输出流，默认从控制台输出
     *
     * 2.
     *  System类的setIn(InputStream is) / setOut(PrintStream ps) 方式重新指定输入和输出的流
     */
    public static void main(String[] args) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                String input = null;
                try {
                    input = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if ("e".equalsIgnoreCase(input) || "exit".equalsIgnoreCase(input)) {
                    System.out.println("程序结束");
                    break;
                }
                System.out.println(input.toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (isr != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 打印流  PrintStream PrintWriter;
     */
    @Test
    public void test() {
        FileOutputStream fos = null;
        PrintStream ps = null;

        try {
            fos = new FileOutputStream("2.txt");
            ps = new PrintStream(fos, true);

            if (ps != null) {
                System.setOut(ps);
            }

            for (int i=0; i<255; i++) {
                System.out.print((char) i);
                if (i%50 == 0) {
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 数据流
     * DataOutputStream DataInputStream
     * 作用：用于读取或写出基本数据类型的变量或字符串
     */
    @Test
    public void test3() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("dict.txt"));
            dos.writeUTF("季海涛");
            dos.flush(); // 刷新操作，将内存中的数据写入文件
            dos.writeInt(23);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文件中存储的基本数据类型和字符串读取到内存中，保存在变量中
     * 读取不同的数据类型的顺序要与当初写入文件时，保存的数据的顺序一致
     */
    @Test
    public void test4() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("dict.txt"));
            System.out.println("name：" + dis.readUTF());
            System.out.println("age：" + dis.readInt());
            System.out.println("graduate：" + dis.readBoolean());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
