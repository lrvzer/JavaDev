package per.study.io;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1、ObjectInputStream和ObjectOutputStream
 * 2、作用：用于存储和读取基本数据类型或对象的处理流，它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来
 * 3、ObjectInputStream和ObjectOutputStream不能序列化static和transient修饰的成员变量
 * 4、要想Java对象是可序列化的，需满足相应的要求，见Person类
 * @author lrwei
 */
public class ObjectInputOutputStreamTest {

    /**
     * 序列化过程：将内存中的对象保存到磁盘中或通过网络传输出去
     * 使用ObjectOutputStream实现
     */
    @Test
    public void useObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush(); // 刷新操作
            oos.writeObject(new Person("小明", 23));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的一个Java对象
     * 使用ObjectInputStream实现
     */
    @Test
    public void useObjectInputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            String str = (String) ois.readObject();
            System.out.println(str);
            Person person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
