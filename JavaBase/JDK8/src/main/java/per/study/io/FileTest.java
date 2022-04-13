package per.study.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 1.File类的一个对象，代表一个文件或一个文件目录（文件夹）
 * 2.File类声明在java.io包下
 * 3.File类涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法
 *   并未涉及到写入或读取文件内容的操作，如果需要读取或写入文件的内容，必须用IO流来完成
 * 4.后续File的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点"
 */
public class FileTest {

    /**
     * boolean createNewFile()：创建文件，如果文件存在，则不创建，返回false
     * boolean mkdir()：创建文件目录，如果此文件目录存在，就不创建了，如果此文件目录的上层不存在，则不创建，返回false
     * boolean mkdirs()：创建文件目录，如果上层目录不存在，一并创建
     *
     * boolean delete()：删除文件或者文件夹
     */
    @Test
    public void test4() throws IOException {
        // 文件创建
        File file1 = new File("hi.txt");
        if (!file1.exists()) {
            file1.createNewFile();
            System.out.println("创建成功");
        } else {
            System.out.println("文件存在");
            file1.delete();
            System.out.println("删除成功");
        }

        // 目录创建
        File file2 = new File("D:\\IO\\IO1");
        boolean mkdir = file2.mkdir();
        boolean mkdirs = file2.mkdirs();

    }

    /**
     * boolean isDirectory()：判断是否是文件目录
     * boolean isFile()：判断是否是文件
     * boolean exists()：判断是否存在
     * boolean canRead()：判断是否可读
     * boolean canWrite()：判断是否可写
     * boolean isHidden()：判断是否隐藏
     */
    @Test
    public void test3() {
        File file1 = new File("hello.txt");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
    }

    /**
     * String getAbsolutePath()：获取绝对路径
     * String getPath()：获取路径
     * String getName()：获取名称
     * String getParent()：获取上层文件目录，若无，返回null
     * long length()：获取文件长度（即：字节数）。不能获取目录的长度
     * long lastModified()：获取最后一次的修改时间，毫秒值
     *
     * String[] list()：获取指定目录下的所有文件或者文件目录的名称数组
     * File[] listFiles()：获取指定目录下的所有文件或者文件目录的File数组
     *
     * boolean renameTo(File dest)：把文件重命名为指定的文件路径
     *         要想保证返回true，需要file1在硬盘中存在，且file3在硬盘中不存在
     */
    @Test
    public void test2() {
        File file1 = new File("hello.txt");
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());

        System.out.println("======================");

        File file2 = new File("E:\\StudyWorkSpace\\JavaSenior\\StudyJavaThread");
        String[] list = file2.list();
        for (String fs: list) {
            System.out.println(fs);
        }
        System.out.println("======================");

        File[] files = file2.listFiles();
        for (File f :files) {
            System.out.println(f);
        }

        File file3 = new File("E:\\hi.txt");
        boolean b = file1.renameTo(file3);
        System.out.println(b);
    }

    /**
     * 1.创建File类的实例
     *   new File(String pathname)
     *   new File(String parent, String child)
     * 2.相对路径：相较于某个路径下指明的路径
     *   绝对路径：包含盘符在内的文件或者目录的路径
     * 3.路径分隔符
     *   public static final char separatorChar --> File类内部动态获取
     *   windows：\\
     *   unix：/
     */
    @Test
    public void test1() {
        File file1 = new File("hello.txt");
        File file2 = new File("E:\\hello.txt");
        System.out.println(file1);
        System.out.println(file2);

        File file3 = new File("E:\\", "Study");
        System.out.println(file3);

        File file4 = new File(file3, "a.txt");
        System.out.println(file4);
    }

}
