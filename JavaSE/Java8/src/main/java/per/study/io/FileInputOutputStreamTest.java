package per.study.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * 结论：
 *  1.对于文本文件（.txt .java .c .cpp），使用字符流处理
 *  2.对于非文本文件（.jpg .mp3 .mp4 .avi .doc .ppt），使用字节流处理
 */
public class FileInputOutputStreamTest {
    // 使用字节流处理文本文件FileInputStream，可能出现乱码
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            File file = new File("1.txt");
            fis = new FileInputStream(file);
            int len;
            byte[] buff = new byte[5];
            while ((len = fis.read(buff)) != -1) {
                System.out.println(new String(buff, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实现图片的赋值操作
     */
    @Test
    public void copyPic() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File srcFile = new File("wallpaper.jpg");
            File destFile = new File("wallpaper.bak.jpg");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            System.out.println("复制结束！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public void copyFile(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File srcFile = new File(src);
            File destFile = new File(dest);

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            System.out.println("复制结束！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    public void testCopyFile() {
        long start = System.currentTimeMillis();
        copyFile("wallpaper.jpg", "wallpaper.copy.jpg");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

}
